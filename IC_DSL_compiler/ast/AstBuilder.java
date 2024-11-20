package ast;

import grammar.IntentDSLLexer;
import grammar.IntentDSLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class AstBuilder {

    public static Node buildAst(String fileIn) throws IOException {
        CharStream input = CharStreams.fromFileName(fileIn);

        IntentDSLLexer lexer = new IntentDSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        IntentDSLParser parser = new IntentDSLParser(tokens);
        IntentDSLParser.ProgramContext tree = parser.program();

        Listener listener = new Listener();
        listener.enterProgram(tree);

        return listener.getRoot();
    }

}
