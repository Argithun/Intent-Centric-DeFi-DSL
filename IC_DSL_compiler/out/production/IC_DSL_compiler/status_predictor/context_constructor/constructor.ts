import * as fs from "fs";
import * as path from "path";

// --- 命令行参数处理 ---
const args = process.argv.slice(2);
const inputFile = args[0] || "../next_block_predictor/predicted_tx_set.json";
const outputFile = args[1] || "future_contexts.json";
const filterTo = args[2]?.toLowerCase(); // 如果存在，则转为小写以便统一比较

const inputPath = path.resolve(__dirname, inputFile);
const outputPath = path.resolve(__dirname, outputFile);

// --- 类型定义 ---
interface FutureContext {
  txSequence: string[];
  blockHeader: {
    timestamp: number;
    coinbase: string;
    basefee: string;
  };
  targetTxIndex: number;
}

// --- 洗牌工具 ---
function shuffleArray<T>(array: T[]): T[] {
  const result = array.slice();
  for (let i = result.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [result[i], result[j]] = [result[j], result[i]];
  }
  return result;
}

// --- 合约地址分组 ---
function groupTransactionsByTo(txSet: any[]): Record<string, any[]> {
  const groups: Record<string, any[]> = {};
  for (const tx of txSet) {
    const to = (tx.to ?? "__contract_creation__").toLowerCase();
    if (!groups[to]) groups[to] = [];
    groups[to].push(tx);
  }
  return groups;
}

// --- 构造上下文 ---
function generateFutureContexts(group: any[], k: number): FutureContext[] {
  const contexts: FutureContext[] = [];

  for (let i = 0; i < k; i++) {
    const shuffled = shuffleArray(group);
    const targetIndex = Math.floor(Math.random() * shuffled.length);

    const blockHeader = {
      timestamp: Math.floor(Date.now() / 1000) + Math.floor(Math.random() * 60),
      coinbase: "0x" + Math.floor(Math.random() * 1e16).toString(16).padStart(40, "0"),
      basefee: BigInt(Math.floor(1e9 + Math.random() * 1e9)).toString()
    };

    contexts.push({
      txSequence: shuffled.map((tx) => tx.hash),
      blockHeader,
      targetTxIndex: targetIndex,
    });
  }

  return contexts;
}

// --- 主程序 ---
async function main() {
  const txSet = JSON.parse(fs.readFileSync(inputPath, "utf-8"));
  const grouped = groupTransactionsByTo(txSet);
  const k = 5;

  const allContexts: FutureContext[] = [];

  const targetGroups = filterTo
    ? grouped[filterTo] ? [grouped[filterTo]] : []
    : Object.values(grouped);

  for (const group of targetGroups) {
    const contexts = generateFutureContexts(group, k);
    allContexts.push(...contexts);
  }

  fs.writeFileSync(outputPath, JSON.stringify(allContexts, null, 2), "utf-8");
  console.log(`✅ Generated ${allContexts.length} future contexts and saved to ${outputPath}`);
}

main().catch((e) => console.error("❌ Error in context constructor:", e));
