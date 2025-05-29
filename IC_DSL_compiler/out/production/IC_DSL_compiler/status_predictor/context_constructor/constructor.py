import json
import random
import time
import argparse
import os
import random
from collections import defaultdict, deque

# --- 参数处理 ---
parser = argparse.ArgumentParser()
parser.add_argument("input_file", help="Input JSON file of transactions")
parser.add_argument("output_file", help="Output JSON file for future contexts")
parser.add_argument("--from_", dest="from_", help="Filter transactions by from address (lowercase)")
parser.add_argument("--to", help="Filter transactions by to address (lowercase)")
args = parser.parse_args()


# --- 类型定义 ---
class FutureContext:
    def __init__(self, tx_sequence, block_header):
        self.txSequence = tx_sequence
        self.blockHeader = block_header


# --- 洗牌工具 ---
def shuffle_array(arr):
    new_arr = arr[:]
    random.shuffle(new_arr)
    return new_arr


# 随机地址生成器
def generate_random_address():
    return "0x" + "".join(random.choices("0123456789abcdef", k=40))


# 为 null 地址分配默认值
def replace_null_with_random(txs):
    for tx in txs:
        if not tx.get("from"):
            tx["from"] = generate_random_address()
        if not tx.get("to"):
            tx["to"] = generate_random_address()
    return txs


# --- 分组：基于地址连通性（from 或 to 相同视为相连） ---
def group_transactions_connected(txs):
    index_map = {i: tx for i, tx in enumerate(txs)}
    address_to_indices = defaultdict(set)

    for idx, tx in index_map.items():
        from_addr = tx["from"].lower()
        to_addr = tx["to"].lower()
        address_to_indices[from_addr].add(idx)
        address_to_indices[to_addr].add(idx)

    visited = [False] * len(txs)
    groups = []

    def bfs(start_idx):
        queue = deque([start_idx])
        group = []
        while queue:
            idx = queue.popleft()
            if visited[idx]:
                continue
            visited[idx] = True
            group.append(index_map[idx])
            from_addr = index_map[idx]["from"].lower()
            to_addr = index_map[idx]["to"].lower()
            for neighbor in address_to_indices[from_addr] | address_to_indices[to_addr]:
                if not visited[neighbor]:
                    queue.append(neighbor)
        return group

    for i in range(len(txs)):
        if not visited[i]:
            groups.append(bfs(i))
    return groups


# --- 上下文构造 ---
def generate_future_contexts(group, k):
    contexts = []
    tx_list_hash = set()
    for _ in range(k):
        shuffled = shuffle_array(group)
        tx_list = [tx["hash"] for tx in shuffled]
        if str(tx_list) in tx_list_hash:
            continue
        tx_list_hash.add(str(tx_list))

        block_header = {
            "timestamp": int(time.time()) + random.randint(0, 60),
            "coinbase": "0x" + hex(random.randint(0, 1 << 80))[2:].zfill(40),
            "basefee": str(random.randint(1_000_000_000, 2_000_000_000))
        }
        context = FutureContext(
            tx_list,
            block_header
        )
        contexts.append(context.__dict__)
    return contexts


# --- 主程序 ---
with open(args.input_file, "r", encoding="utf-8") as f:
    txs = json.load(f)
txs = replace_null_with_random(txs)

filtered = txs
target_from = args.from_.lower()
target_to = args.to.lower()

# 分组 + 构造上下文
groups = group_transactions_connected(filtered)
k = 10
all_contexts = []
for group in groups:
    has_target = False
    for tx in group:
        if tx["from"] == target_from and tx["to"] == target_to:
            has_target = True
            break
    if not has_target:
        continue
    all_contexts.extend(generate_future_contexts(group, k))

# 输出结果
with open(args.output_file, "w", encoding="utf-8") as f:
    json.dump(all_contexts, f, indent=2)

print(f"Generated {len(all_contexts)} future contexts and saved to {args.output_file}")
