import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SimpleExprMain {

    public static void run(String expr) throws Exception {
        CharStream in = CharStreams.fromFileName(expr);
        SimpleExprLexer lexer = new SimpleExprLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleExprParser parser = new SimpleExprParser(tokens);
        ParseTree parseTree = parser.prog();
        System.out.println(parseTree.toStringTree(parser));
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        SimpleExprBaseListener simpleExprBaseListener = new SimpleExprBaseListener();
        parseTreeWalker.walk(simpleExprBaseListener, parseTree);
    }

    public static void main(String[] args) throws Exception {
        run("/Users/chenshuai/github/antlr4-maven-example/src/main/resources/expr.txt");
    }
}
