# OmniIntent: Intent-Centric DeFi DSL and Trusted Execution Prototype

This repository contains the code artifact for the paper **"OmniIntent: A Trusted Intent-Centric Framework for User-Friendly Web3"**.

The implementation centers on:

- **ICL (Intent-Centric Language)** for structured DeFi intent expression.
- **Trusted Intent Compiler** (prototype form) that parses ICL and maps it to executable transactions.
- **Execution Optimizer** with dependency-aware scheduling and optional parallel submission.
- **Feasibility Checker** (mempool-aware pre-execution predictor) for risk warning before on-chain submission.

## 1. Repository Layout

- `IC_DSL_compiler/`
  - `grammar/grammar_design/IntentDSL.g4`: ICL grammar (ANTLR4).
  - `ast/`: AST node definitions and parser listener-based AST builder.
  - `Compiler.java`: main entry of compile-and-execute pipeline.
  - `transaction/`: transaction generation and submission logic.
  - `optimize/dependency/`: dependency graph construction and optimization.
  - `tool/`: condition checks, nonce/private-key helpers, predictor integration.
  - `settings/`: runtime configuration (RPC/API/proxy/mode flags).
  - `status_predictor/`: TypeScript next-block predictor and context constructor.
  - `main.ic`, `graph.ic`, `test_language/*.ic`, `test_transaction/*.ic`: intent scripts and benchmark samples.

## 2. Paper-to-Code Mapping

The code corresponds to the architecture in the paper as follows:

- **ICL language design (Section 4)**  
  Implemented by `grammar/grammar_design/IntentDSL.g4`, generated parser in `grammar/`, and AST building in `ast/`.

- **Trusted intent compilation (Section 5)**  
  Implemented by `Compiler.java` + `transaction/TransGenerator.java` and operation-specific builders under `transaction/ops/`.

- **Execution optimizer (Section 6.1)**  
  Implemented by `optimize/dependency/DependencyAnalysis.java` and `DependencyGraph.java`; serial/parallel execution in `transaction/TransSubmitter.java` and `transaction/TransSubmitterParallel.java`.

- **Feasibility checker (Section 6.2)**  
  Java side in `tool/PredictConditionCheck.java`, context simulation in `status_predictor/context_constructor/ContextConstructor.java`, and mempool prediction in `status_predictor/next_block_predictor/predictor.ts`.

## 3. Environment and Dependencies

### Java side

- JDK **11** (project language level is Java 11).
- IntelliJ IDEA project files are included (`IC_DSL_compiler/.idea`).
- Main libraries used by the project:
  - ANTLR4 (`antlr-4.12.0-complete`)
  - Web3j (`org.web3j:core:4.5.10`)
  - GraphStream (`graphstream-gs-core`, `graphstream-gs-ui-swing`)
  - JSON (`org.json:json`)
  - BouncyCastle (`bcprov-jdk15on`)

> Note: The Java module is currently managed as an IntelliJ project (no Maven/Gradle build file in repository root).

### TypeScript side (status predictor)

- Node.js 18+ recommended.
- In `IC_DSL_compiler/status_predictor/`:
  - dependency: `ethers`
  - run with `npx tsx ...`

Install once:

```bash
cd IC_DSL_compiler/status_predictor
npm install
```

## 4. Configuration Before Running

Edit `IC_DSL_compiler/settings/Settings.java`:

- `TEST_MODE`: `true` for Sepolia, `false` for Mainnet.
- `PARALLEL_MODE`: enable dependency-aware parallel submission.
- `IF_PRE_EXECUTION`: enable mempool-aware feasibility check before submit.
- `INFURA_API_KEY`, `NFTSCAN_API_KEY`, `OPENSEA_API_KEY`: replace with your own keys.
- `IF_USE_PROXY`, `PROXY_HOST_NAME`, `PROXY_PORT`: configure network proxy if needed.

Important security note:

- ICL scripts use `switch to account[...]` with raw private key literal for prototype testing.
- Do **NOT** use production wallets or high-value keys in this artifact setup.

## 5. Quick Start (Run Compiler Pipeline)

1. Open `IC_DSL_compiler` in IntelliJ IDEA.
2. Configure runtime settings in `settings/Settings.java`.
3. Select an input ICL file in `Compiler.java`:
   - default: `main.ic`
   - alternatives: `graph.ic`, `test_language/*.ic`, `test_transaction/*.ic`
4. Run `Compiler.main()`.
5. Observe logs for:
   - trigger-condition waiting
   - transaction construction
   - dependency-based scheduling
   - on-chain submission results

## 6. ICL Script Format (Examples)

Typical examples in this repository:

```icl
switch to account[e55ff...];
transfer 0.001 ETH from wallet[0x...] to wallet[0x...] checking fee < 400000;
swap 0.0001 ETH from wallet[0x...] for WETH on Uniswap;
stake 0.0001 WETH from wallet[0x...] using low-risk strategy;
trigger balance wallet[0x...] > 400000 USDT then add 400000 USDC, 400000 USDT to Sushiswap receiving liquidity token to wallet[0x...];
```

Supported intent categories include:

- Transfer
- Borrow / Repay
- Swap
- Add / Remove Liquidity
- Stake (simple/strategy-based)
- NFT Buy / Sell (simple/strategy-based)
- Trigger conditions and runtime constraints (`trigger ... then`, `checking ...`)

## 7. Feasibility Checker (Optional)

When `IF_PRE_EXECUTION=true`, Java submitter invokes the status predictor workflow:

1. Build candidate next-block transaction set from mempool (`predictor.ts`).
2. Construct randomized execution contexts around target tx.
3. Simulate contexts and issue pre-submission warning.

You can run predictor standalone:

```bash
cd IC_DSL_compiler/status_predictor
npx tsx next_block_predictor/predictor.ts
```

If you need a local dev mempool source, a basic geth dev command is documented in:

- `IC_DSL_compiler/status_predictor/next_block_predictor/README.md`

## 8. Reproducing Paper Evaluation Inputs

The repository includes intent corpora used for large-scale transaction scheduling tests:

- `IC_DSL_compiler/test_language/main_<N>_<DI>.ic`
  - `N`: intent count (e.g., 10/30/50/70/100)
  - `DI`: dependency index level (e.g., 0.0/0.3/0.5/0.7/1.0)

These files are useful for reproducing trends reported in the paper (e.g., serial vs. parallel execution latency under different dependency levels).

## 9. Current Prototype Scope

- This artifact is a research prototype aligned with the paper and prioritizes demonstrability.
- Some components are environment-specific (e.g., API keys, proxy setup, RPC provider choice).
- The TEE protocol described in the paper is represented in architecture and workflow; deployment-grade enclave hardening/attestation integration should be treated as future engineering work.

## 10. Citation

If you use this repository, please cite:

- **OmniIntent: A Trusted Intent-Centric Framework for User-Friendly Web3** (Middleware 2026 submission artifact).
