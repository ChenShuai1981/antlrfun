import java.util.HashMap;
import java.util.Map;

public class LabeledCalculatorVisitor extends LabeledExprBaseVisitor<Integer> {

    private static final int MUL = 4;
    private static final int ADD = 6;

    private Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext assignContext) {
       String id = assignContext.ID().getText();
       Integer value = visit(assignContext.expr());
       memory.put(id, value);
       return value;
    }

    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext printExprContext) {
        Integer value = visit(printExprContext.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitInt(LabeledExprParser.IntContext intContext) {
        return Integer.parseInt(intContext.INT().getText());
    }

    @Override
    public Integer visitId(LabeledExprParser.IdContext idContext) {
        String id = idContext.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        } else {
            return 0;
        }
    }

    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext mulDivContext) {
        Integer left = visit(mulDivContext.expr(0));
        Integer right = visit(mulDivContext.expr(1));
        if (mulDivContext.op.getType() == MUL) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext addSubContext) {
        Integer left = visit(addSubContext.expr(0));
        Integer right = visit(addSubContext.expr(1));
        if (addSubContext.op.getType() == ADD) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitParens(LabeledExprParser.ParensContext parensContext) {
        return visit(parensContext.expr());
    }
}
