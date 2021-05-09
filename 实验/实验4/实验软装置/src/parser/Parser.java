package parser;

import exceptions.*;
import expr.ArithExpr;
import expr.BoolExpr;
import expr.Expr;
import expr.Terminal;
import token.DollarToken;

import java.util.ArrayList;

/**
 * Parser.
 */
public class Parser {
    private final Scanner scanner;
    private final Reducer reducer;
    private final ArrayList<Expr> stack;
    private Terminal lookahead;

    /**
     * 构造函数
     *
     * @param input 输入的表达式
     * @throws EmptyExpressionException 空表达式错误
     */
    public Parser(String input) throws EmptyExpressionException {
        if (input.isEmpty())
            throw new EmptyExpressionException();
        scanner = new Scanner(input);
        reducer = new Reducer();
        stack = new ArrayList<>();
        stack.add(new Terminal(new DollarToken()));
    }

    /**
     * 被calculator调用的进行parse动作的函数
     *
     * @return 计算结果
     * @throws ExpressionException 错误集合
     */
    public double parse() throws ExpressionException {
        lookahead = new Terminal(scanner.getNextToken());
        while (true) {
            Terminal topMostTerminal = getTopMostTerminal();
            switch (OPPTable.table[topMostTerminal.getTag()][lookahead.getTag()]) {
                case 0:
                    shift();
                    break;
                case 1:
                    reduce();
                    break;
                case 2:
                    return accept();
                case -1:
                    throw new MissingOperatorException();
                case -2:
                    throw new MissingRightParenthesisException();
                case -3:
                    throw new MissingLeftParenthesisException();
                case -4:
                    throw new FunctionCallException();
                case -5:
                    throw new TypeMismatchedException();
                case -6:
                    throw new MissingOperandException();
                case -7:
                    throw new TrinaryOperationException();
            }
        }
    }

    /**
     * opp表，顺序是num,bool,+-,* /,-,^,fuc,(,,,),Relation,!,&,|,?,:,$
     * 值为0代表shift，1为reduce，2为accept
     * 其余为各种错误，具体见上面的switch case
     */
    public static class OPPTable {
        public final static int[][] table = new int[][]{
                {-1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -5, 1, 1, 1, 1, 1},
                {-1, -1, -5, -5, -5, -5, -1, -1, -5, 1, -5, -1, 1, 1, 1, 1, 1},
                {0, -5, 1, 0, 0, 0, 0, 0, 1, 1, 1, -5, -5, -5, -5, 1, 1},
                {0, -5, 1, 1, 0, 0, 0, 0, 1, 1, 1, -5, -5, -5, -5, 1, 1},
                {0, -5, 1, 1, 0, 1, 0, 0, 1, 1, 1, -5, -5, -5, -5, 1, 1},
                {0, -5, 1, 1, 0, 0, 0, 0, 1, 1, 1, -5, -5, -5, -5, 1, 1},
                {-3, -3, -3, -3, -3, -3, -3, 0, -3, 1, -3, -3, -3, -3, -3, 1, -3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2},
                {0, -5, 0, 0, 0, 0, 0, 0, 0, 0, -5, -5, -5, -5, 0, 0, -2},
                {-1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1},
                {0, -5, 0, 0, 0, 0, 0, 0, -5, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, -5, -5, -5, -5, -5, 0, -5, 1, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, -5, -5, -5, -5, -5, 0, -5, 1, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, -5, -5, -5, -5, -5, 0, -5, 1, 0, 0, 0, 1, 1, 1, 1},
                {0, -7, 0, 0, 0, 0, 0, 0, -7, -6, 0, -7, -7, -7, 0, 0, -7},
                {0, -7, 0, 0, 0, 0, 0, 0, 1, 1, 0, -7, -7, -7, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, -3, -3, 0, 0, 0, 0, 0, -7, 2}
        };
    }

    private Terminal getTopMostTerminal() {
        int i = stack.size() - 1;
        while (true) {
            Expr temp = stack.get(i);
            if (temp.isTerminal())
                return (Terminal) temp;
            else
                i--;
        }
    }

    private double accept() throws TypeMismatchedException {
        Expr k = stack.get(stack.size() - 1);
        if (k.getTag() == Scanner.kingOfChar.BoolExpr) {
            System.out.println(((BoolExpr) k).getValue());
            throw new TypeMismatchedException();
        }
        return ((ArithExpr) k).getValue();
    }

    private void reduce() throws ExpressionException {
        Terminal topMostTerminal = getTopMostTerminal();
        switch (topMostTerminal.getTag()) {
            case Scanner.kingOfChar.NUM:
                reducer.numReducer(stack);
                break;
            case Scanner.kingOfChar.BOOL:
                reducer.boolReducer(stack);
                break;
            case Scanner.kingOfChar.ADDSUB:
                reducer.add_subReducer(stack);
                break;
            case Scanner.kingOfChar.MULDIV:
                reducer.mul_divReducer(stack);
                break;
            case Scanner.kingOfChar.NOT:
                reducer.notReducer(stack);
                break;
            case Scanner.kingOfChar.NEG:
                reducer.negReducer(stack);
                break;
            case Scanner.kingOfChar.POWER:
                reducer.powReducer(stack);
                break;
            case Scanner.kingOfChar.AND:
            case Scanner.kingOfChar.OR:
                reducer.and_orReducer(stack);
                break;
            case Scanner.kingOfChar.RE:
                reducer.reReducer(stack);
                break;
            case Scanner.kingOfChar.COLON:
                reducer.colonReducer(stack);
                break;
            case Scanner.kingOfChar.RP:
                reducer.rpReducer(stack);
                break;
            default:
                System.out.println(topMostTerminal.getTag());
                break;
        }
    }

    private void shift() throws ExpressionException {
        stack.add(lookahead);
        lookahead = new Terminal(scanner.getNextToken());
    }

}