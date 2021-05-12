package parser;

import exceptions.*;
import expr.*;
import token.Boolean;
import token.Decimal;

import java.util.ArrayList;

/**
 * 各种reduce操作
 */
public class Reducer {
    /**
     * Num reducer.
     *
     * @param stack 用于存放token的栈
     */
    public void numReducer(ArrayList<Expr> stack) {
        int i = stack.size() - 1;
        Terminal k = (Terminal) stack.get(i);
        ArithExpr arithExpr = new ArithExpr((Decimal) k.token);
        stack.remove(i);
        stack.add(arithExpr);
    }

    /**
     * Bool reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void boolReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Terminal t = (Terminal) stack.get(i);
        BoolExpr b = new BoolExpr((Boolean) t.token);
        stack.remove(i);
        stack.add(b);
    }

    /**
     * Neg reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void negReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr k = stack.get(i);
        if (k.getTag() != Scanner.kindOfChar.ArithExpr)
            throw new TypeMismatchedException();
        double v = ((Decimal) k.token).getValue();
        ArithExpr arithExpr = new ArithExpr(-v);
        stack.remove(i);
        stack.remove(i - 1);
        stack.add(arithExpr);
    }

    /**
     * Not reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void notReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i);
        if (a.getTag() != Scanner.kindOfChar.BoolExpr)
            throw new TypeMismatchedException();
        BoolExpr be = new BoolExpr(!((BoolExpr) a).getValue());
        stack.remove(i);
        stack.remove(i - 1);
        stack.add(be);
    }

    /**
     * Add sub reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void add_subReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i - 2);
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i);
        if ((b.tag == Scanner.kindOfChar.ADDSUB))
            throw new MissingOperandException();
        if (a.getTag() == Scanner.kindOfChar.BoolExpr)
            throw new TypeMismatchedException();
        else if (a.getTag() != Scanner.kindOfChar.ArithExpr)
            throw new MissingOperandException();
        ArithExpr arithExpr;
        if (o.token.getString().equals("+"))
            arithExpr = new ArithExpr(((ArithExpr) a).getValue() + ((ArithExpr) b).getValue());
        else
            arithExpr = new ArithExpr(((ArithExpr) a).getValue() - ((ArithExpr) b).getValue());
        stack.remove(i);
        stack.remove(i - 1);
        stack.remove(i - 2);
        stack.add(arithExpr);
    }

    /**
     * And or reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void and_orReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i - 2);
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i);
        if ((b.getTag() == Scanner.kindOfChar.AND) || (b.getTag() == Scanner.kindOfChar.OR))
            throw new MissingOperandException();
        if (a.getTag() != Scanner.kindOfChar.BoolExpr || b.getTag() != Scanner.kindOfChar.BoolExpr)
            throw new TypeMismatchedException();
        BoolExpr be;
        if (o.getTag() == Scanner.kindOfChar.AND)
            be = new BoolExpr(((BoolExpr) a).getValue() && ((BoolExpr) b).getValue());
        else
            be = new BoolExpr(((BoolExpr) a).getValue() || ((BoolExpr) b).getValue());
        stack.remove(i);
        stack.remove(i - 1);
        stack.remove(i - 2);
        stack.add(be);
    }

    /**
     * Mul div reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void mul_divReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i - 2);
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i);
        if ((b.getTag() == Scanner.kindOfChar.MULDIV))
            throw new MissingOperandException();
        if (a.getTag() != Scanner.kindOfChar.ArithExpr || b.getTag() != Scanner.kindOfChar.ArithExpr)
            throw new TypeMismatchedException();
        ArithExpr ae;
        if (o.token.getString().equals("*"))
            ae = new ArithExpr(((ArithExpr) a).getValue() * ((ArithExpr) b).getValue());
        else if (((ArithExpr) b).getValue() == 0)
            throw new DividedByZeroException();
        else
            ae = new ArithExpr(((ArithExpr) a).getValue() / ((ArithExpr) b).getValue());
        stack.remove(i);
        stack.remove(i - 1);
        stack.remove(i - 2);
        stack.add(ae);
    }

    /**
     * Colon reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void colonReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i);
        Expr b = stack.get(i - 2);
        Expr c = stack.get(i - 3);
        Expr d = stack.get(i - 4);
        if (a.getTag() != Scanner.kindOfChar.ArithExpr || b.getTag() != Scanner.kindOfChar.ArithExpr || d.getTag() != Scanner.kindOfChar.BoolExpr)
            throw new TypeMismatchedException();
        if (c.getTag() != Scanner.kindOfChar.QM)
            throw new exceptions.TrinaryOperationException();
        if (((BoolExpr) d).getValue()) {
            stack.remove(i);
            stack.remove(i - 1);
            stack.remove(i - 3);
            stack.remove(i - 4);
        } else {
            stack.remove(i - 1);
            stack.remove(i - 2);
            stack.remove(i - 3);
            stack.remove(i - 4);
        }
    }

    /**
     * Pow reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void powReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i - 2);
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i);
        if ((b.getTag() == Scanner.kindOfChar.POWER))
            throw new MissingOperandException();
        if (a.getTag() != Scanner.kindOfChar.ArithExpr || b.getTag() != Scanner.kindOfChar.ArithExpr)
            throw new TypeMismatchedException();
        ArithExpr ae;
        ae = new ArithExpr(Math.pow(((ArithExpr) a).getValue(), ((ArithExpr) b).getValue()));
        stack.remove(i);
        stack.remove(i - 1);
        stack.remove(i - 2);
        stack.add(ae);
    }

    /**
     * Relation reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void relationReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr a = stack.get(i - 2);
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i);
        if (b.getTag() == Scanner.kindOfChar.RE)
            throw new MissingOperandException();
        if (a.getTag() != Scanner.kindOfChar.ArithExpr || b.getTag() != Scanner.kindOfChar.ArithExpr)
            throw new TypeMismatchedException();
        BoolExpr be = null;
        switch (o.token.getString()) {
            case "=":
                be = new BoolExpr(((ArithExpr) a).getValue() == ((ArithExpr) b).getValue());
                break;
            case "<>":
                be = new BoolExpr(((ArithExpr) a).getValue() != ((ArithExpr) b).getValue());
                break;
            case "<=":
                be = new BoolExpr(((ArithExpr) a).getValue() <= ((ArithExpr) b).getValue());
                break;
            case "<":
                be = new BoolExpr(((ArithExpr) a).getValue() < ((ArithExpr) b).getValue());
                break;
            case ">=":
                be = new BoolExpr(((ArithExpr) a).getValue() >= ((ArithExpr) b).getValue());
                break;
            case ">":
                be = new BoolExpr(((ArithExpr) a).getValue() > ((ArithExpr) b).getValue());
                break;
        }
        stack.remove(i);
        stack.remove(i - 1);
        stack.remove(i - 2);
        stack.add(be);
    }

    /**
     * Rp reducer.
     *
     * @param stack 用于存放token的栈
     * @throws ExpressionException the expression exception
     */
    public void rpReducer(ArrayList<Expr> stack) throws ExpressionException {
        int i = stack.size() - 1;
        Expr o = stack.get(i - 1);
        Expr b = stack.get(i - 2);
        Expr c = stack.get(i - 3);
        if (o.getTag() == Scanner.kindOfChar.RE || o.getTag() == Scanner.kindOfChar.COMMA)
            throw new MissingOperandException();
        else if (o.getTag() == Scanner.kindOfChar.BoolExpr) {
            stack.remove(i);
            stack.remove(i - 2);
            return;
        } else if (o.getTag() == Scanner.kindOfChar.ArithExprList) {
            if (c.getTag() != Scanner.kindOfChar.ArithExpr)
                throw new TypeMismatchedException();
            ArithExprList ael = new ArithExprList((ArithExpr) c, (ArithExprList) o);
            Expr d = stack.get(i - 4);
            Expr f = stack.get(i - 5);
            ArithExpr ae = null;
            if (d.getTag() == Scanner.kindOfChar.LP && f.getTag() == Scanner.kindOfChar.FUNC) {
                switch (f.token.getString()) {
                    case "max":
                        ae = new ArithExpr(ael.max);
                        break;
                    case "min":
                        ae = new ArithExpr(ael.min);
                        break;
                    default:
                        throw new FunctionCallException();
                }
                stack.remove(i);
                stack.remove(i - 1);
                stack.remove(i - 2);
                stack.remove(i - 3);
                stack.remove(i - 4);
                stack.remove(i - 5);
                stack.add(ae);
            } else {
                stack.remove(i - 1);
                stack.remove(i - 2);
                stack.set(i - 3, ael);
            }
            return;
        } else if (o.getTag() == Scanner.kindOfChar.ArithExpr) {
            if (b.getTag() == Scanner.kindOfChar.COMMA) {
                ArithExprList ael = new ArithExprList((ArithExpr) o);
                stack.set(i - 1, ael);
                return;
            } else if (c.getTag() == Scanner.kindOfChar.FUNC) {
                if (o.getTag() != Scanner.kindOfChar.ArithExpr)
                    throw new TypeMismatchedException();
                ArithExpr ae;
                switch (c.token.getString()) {
                    case "sin":
                        ae = new ArithExpr(Math.sin(((ArithExpr) o).getValue()));
                        break;
                    case "cos":
                        ae = new ArithExpr(Math.cos(((ArithExpr) o).getValue()));
                        break;
                    default:
                        throw new MissingOperandException();
                }
                stack.remove(i);
                stack.remove(i - 1);
                stack.remove(i - 2);
                stack.remove(i - 3);
                stack.add(ae);
                return;
            } else {
                System.out.println(1);
                stack.remove(i);
                stack.remove(i - 2);
                return;
            }
        }
        throw new SyntacticException();
    }
}
