package status_predictor.context_constructor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class ContextConstructor {

    private static final Random random = new Random();
    private static final int K = 10; // 每组生成多少个 future context

    public static void contextConstructor(String targetHash,
                                          String predictedTransactionPath,
                                          String futureContextPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // 读取所有交易
        JsonNode txsNode = mapper.readTree(new File(predictedTransactionPath));
        List<ObjectNode> txs = new ArrayList<>();
        for (JsonNode tx : txsNode) {
            txs.add((ObjectNode) tx);
        }

        // 替换 from/to 为空的交易
        replaceNullWithRandom(txs);

        // 分组（基于 from/to 地址连通性）
        List<List<ObjectNode>> groups = groupTransactionsConnected(txs, targetHash);

        // 寻找包含 targetHash 的组
        List<ObjectNode> targetGroup = null;
        for (List<ObjectNode> group : groups) {
            for (ObjectNode tx : group) {
                if (tx.get("hash").asText().equals(targetHash)) {
                    targetGroup = group;
                    break;
                }
            }
            if (targetGroup != null) {
                break;
            }
        }

        if (targetGroup == null) {
            throw new RuntimeException("Target hash not found in any group");
        }

        // 构建 future contexts
        ArrayNode allContexts = mapper.createArrayNode();
        Set<String> seenSequences = new HashSet<>();

        for (int i = 0; i < K; i++) {
            List<ObjectNode> shuffled = new ArrayList<>(targetGroup);
            Collections.shuffle(shuffled);

            List<String> txHashList = new ArrayList<>();
            for (ObjectNode tx : shuffled) {
                txHashList.add(tx.get("hash").asText());
            }

            String sequenceKey = String.join(",", txHashList);
            if (seenSequences.contains(sequenceKey)) {
                continue;
            }
            seenSequences.add(sequenceKey);

            ObjectNode blockHeader = mapper.createObjectNode();
            blockHeader.put("timestamp", System.currentTimeMillis() / 1000L + random.nextInt(60));
            blockHeader.put("coinbase", generateRandomAddress());
            blockHeader.put("basefee", String.valueOf(1_000_000_000L + (long) random.nextInt(1_000_000_000)));

            ObjectNode context = mapper.createObjectNode();
            ArrayNode txSequence = mapper.createArrayNode();
            for (String hash : txHashList) {
                txSequence.add(hash);
            }
            context.set("txSequence", txSequence);
            context.set("blockHeader", blockHeader);

            allContexts.add(context);
        }

        // 写入输出文件
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(futureContextPath), allContexts);
        System.out.println("Generated " + allContexts.size() + " future contexts and saved to " + futureContextPath);
    }

    // 替换 null from/to 地址
    private static void replaceNullWithRandom(List<ObjectNode> txs) {
        for (ObjectNode tx : txs) {
            if (!tx.hasNonNull("from") || tx.get("from").asText().isEmpty()) {
                tx.put("from", generateRandomAddress());
            }
            if (!tx.hasNonNull("to") || tx.get("to").asText().isEmpty()) {
                tx.put("to", generateRandomAddress());
            }
        }
    }

    // 基于地址连通性进行分组
    private static List<List<ObjectNode>> groupTransactionsConnected(List<ObjectNode> txs, String targetHash) {
        Map<Integer, ObjectNode> indexMap = new HashMap<>();
        Map<String, Set<Integer>> addressToIndices = new HashMap<>();

        for (int i = 0; i < txs.size(); i++) {
            ObjectNode tx = txs.get(i);
            indexMap.put(i, tx);

            String from = tx.get("from").asText().toLowerCase();
            String to = tx.get("to").asText().toLowerCase();

            addressToIndices.computeIfAbsent(from, k -> new HashSet<>()).add(i);
            addressToIndices.computeIfAbsent(to, k -> new HashSet<>()).add(i);
        }

        boolean[] visited = new boolean[txs.size()];
        List<List<ObjectNode>> groups = new ArrayList<>();

        for (int i = 0; i < txs.size(); i++) {
            if (!visited[i] && txs.get(i).get("hash").asText().equals(targetHash)) {
                groups.add(bfs(i, indexMap, addressToIndices, visited));
            }
        }

        return groups;
    }

    private static List<ObjectNode> bfs(int startIdx,
                                        Map<Integer, ObjectNode> indexMap,
                                        Map<String, Set<Integer>> addressToIndices,
                                        boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        List<ObjectNode> group = new ArrayList<>();
        queue.add(startIdx);

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (visited[idx]) continue;
            visited[idx] = true;
            group.add(indexMap.get(idx));

            String from = indexMap.get(idx).get("from").asText().toLowerCase();
            String to = indexMap.get(idx).get("to").asText().toLowerCase();

            Set<Integer> neighbors = new HashSet<>();
            neighbors.addAll(addressToIndices.getOrDefault(from, Collections.emptySet()));
            neighbors.addAll(addressToIndices.getOrDefault(to, Collections.emptySet()));

            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                }
            }
        }
        return group;
    }

    // 生成随机地址
    private static String generateRandomAddress() {
        byte[] addr = new byte[20];
        random.nextBytes(addr);
        StringBuilder sb = new StringBuilder("0x");
        for (byte b : addr) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
