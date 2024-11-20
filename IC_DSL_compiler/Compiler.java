import ast.AstBuilder;
import ast.Node;

public class Compiler {

    public static void main(String[] args) throws Exception {
        Node intentAst = AstBuilder.buildAst("main.ic");

        // TODO intent ast to transaction
    }


}
