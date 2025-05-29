### 启动 Geth 开发节点
```BASH
geth --dev --http --http.api "eth,net,web3,txpool" --http.port 8545
```

### 连接 Geth 服务
```BASH
geth attach http://localhost:8545
eth.accounts        // 查看开发账户
eth.getBalance(eth.accounts[0])  // 查看余额
eth.sendTransaction({
  from: eth.accounts[0],
  to: eth.accounts[0],
  value: web3.toWei(0.01, "ether")
})
```

### 运行 next-block predictor
```BASH
npx tsx predictor.ts
# or
npx tsx file:///C:/IntentCentricDSL/Intent-Centric-DeFi-DSL/IC_DSL_compiler/status_predictor/predictor.ts
```