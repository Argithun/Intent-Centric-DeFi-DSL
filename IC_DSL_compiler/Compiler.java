import ast.AstBuilder;
import ast.Node;
import optimize.dependency.DependencyAnalysis;
import optimize.dependency.DependencyGraph;
import settings.Settings;
import tool.NonceManager;
import tool.PrivateKeyManager;
import transaction.TransSubmitter;
import transaction.TransSubmitterParallel;


public class Compiler {
    public static void main(String[] args) throws Exception {

        // Node intentAst = AstBuilder.buildAst("graph.ic");
         Node intentAst = AstBuilder.buildAst("main.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_10_0.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_10_0.3.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_10_0.5.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_10_0.7.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_10_1.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_30_0.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_30_0.3.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_30_0.5.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_30_0.7.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_30_1.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_50_0.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_50_0.3.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_50_0.5.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_50_0.7.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_50_1.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_70_0.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_70_0.3.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_70_0.5.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_70_0.7.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_70_1.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_100_0.0.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_100_0.3.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_100_0.5.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_100_0.7.ic");
        // Node intentAst = AstBuilder.buildAst("./test_language/main_100_1.0.ic");

        // Node intentAst = AstBuilder.buildAst("./test_transaction/transfer.ic");
        // Node intentAst = AstBuilder.buildAst("./test_transaction/swap.ic");
        // Node intentAst = AstBuilder.buildAst("./test_transaction/main_10.ic");

        PrivateKeyManager.setPrivateKey(intentAst);
        NonceManager.initAccountToNonce(intentAst);
        DependencyGraph dependencyGraph = DependencyAnalysis.genDependencyGraph(intentAst);

        // System.out.println("Start submit ...");
        // long startTime = System.currentTimeMillis();  // 记录开始时间

        if (Settings.PARALLEL_MODE) {
            TransSubmitterParallel.submitTransactionsParallel(dependencyGraph);
        } else {
            TransSubmitter.submitTransactions(intentAst, dependencyGraph);
        }

        // long endTime = System.currentTimeMillis();  // 记录结束时间
        // System.out.println("执行时间: " + (endTime - startTime) + " ms");

    }


}
