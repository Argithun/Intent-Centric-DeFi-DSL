import { ethers } from "ethers";
import * as fs from "fs";
import * as path from "path";

// --- 读取命令行参数 ---
const args = process.argv.slice(2);
const inputFile = args[0] || "predict_input.json";
const outputFile = args[1] || "predicted_tx_set.json";

const injectedTxPath = path.resolve(__dirname, inputFile);
let injectedTx: any | null = null;

if (fs.existsSync(injectedTxPath)) {
  injectedTx = JSON.parse(fs.readFileSync(injectedTxPath, "utf-8"));
}

// --- 连接本地节点 ---
// const provider = new ethers.JsonRpcProvider("http://127.0.0.1:8545");
// const provider = new ethers.JsonRpcProvider("https://eth.drpc.org")
// const provider = new ethers.JsonRpcProvider("https://sepolia.drpc.org")
// const provider = new ethers.JsonRpcProvider("https://eth-sepolia.nodereal.io/v1/07a45fc0ecc54a2082fe504eee5123af")
const provider = new ethers.JsonRpcProvider("https://docs-demo.quiknode.pro/")

const BLOCK_GAS_LIMIT = 30_000_000;


// --- 简单估算函数 ---
function estimateGas(tx: any): number {
  if (!tx.to) return 100_000;
  return 50_000;
}

// --- 获取 Pending 交易 ---
async function getPendingTransactions(): Promise<any[]> {
  const txpool = await provider.send("txpool_content", []);
  const pending: any[] = [];

  for (const account in txpool.pending) {
    const nonces = txpool.pending[account];
    for (const nonce in nonces) {
      pending.push(nonces[nonce]);
    }
  }

  return pending;
}

// --- 预测交易集合 ---
async function predictTxSet(): Promise<any[]> {
  const txs = await getPendingTransactions();

  const sorted = txs.sort((a, b) => {
    const gasA = BigInt(a.gasPrice ?? "0");
    const gasB = BigInt(b.gasPrice ?? "0");
    return gasB > gasA ? 1 : gasB < gasA ? -1 : Math.random() - 0.5;
  });

  const selected: any[] = [];
  let gasUsed = 0;

  for (const tx of sorted) {
    const est = estimateGas(tx);
    if (gasUsed + est > BLOCK_GAS_LIMIT) break;
    selected.push(tx);
    gasUsed += est;
  }

  if (injectedTx) {
    selected.push(injectedTx);
  }

  return selected;
}

// --- 主程序 ---
async function main() {
  console.log("⏳ Fetching and predicting next block Tx set...");
  const txSet = await predictTxSet();

  console.log(`✅ Predicted ${txSet.length} transactions`);
  txSet.slice(0, 10).forEach((tx, i) => {
    console.log(
        `${i + 1}. From: ${tx.from} | To: ${tx.to} | GasPrice: ${ethers.formatUnits(tx.gasPrice, "gwei")} Gwei`
    );
  });

  const outputPath = path.resolve(__dirname, outputFile);
  fs.writeFileSync(outputPath, JSON.stringify(txSet, null, 2), "utf-8");
  console.log(`💾 Saved predicted Tx set to ${outputPath}`);
}

main().catch((e) => console.error("❌ Error:", e));
