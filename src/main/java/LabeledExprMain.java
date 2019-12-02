import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class LabeledExprMain {

    public static void run(String expr) throws Exception {
        CharStream in = CharStreams.fromFileName(expr);

        LabeledExprLexer lexer = new LabeledExprLexer(in);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LabeledExprParser parser = new LabeledExprParser(tokens);

        ParseTree parseTree = parser.prog();
        System.out.println(parseTree.toStringTree(parser));

        LabeledExprBaseVisitor labeledExprBaseVisitor = new LabeledExprBaseVisitor();
        labeledExprBaseVisitor.visit(parseTree);

//        LabeledCalculatorVisitor labeledCalculatorVisitor = new LabeledCalculatorVisitor();
//        labeledCalculatorVisitor.visit(parseTree);
    }

    public static void main(String[] args) throws Exception {
        run("/Users/chenshuai/github/antlr4-maven-example/src/main/resources/expr.txt");
    }

}
