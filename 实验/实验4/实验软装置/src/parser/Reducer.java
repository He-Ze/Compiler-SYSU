/**
 * 
 */
package parser;

import java.util.ArrayList;

import exceptions.DividedByZeroException;
import exceptions.ExpressionException;
import exceptions.FunctionCallException;
import exceptions.MissingOperandException;
import exceptions.SyntacticException;
import exceptions.TypeMismatchedException;
import token.BooleanToken;
import token.DecimalToken;
import token.OperatorToken;
import scanner.Tag;
import expr.ArithExpr;
import expr.ArithExprList;
import expr.BoolExpr;
import expr.Expr;
import expr.Terminal;

/**
 * 一个封装了各种类型reduce的容器
 * @author Aaron-Qiu
 *
 */
public class Reducer {
	/**
	 * Reducer 构造函数，无特别内容
	 */
	public Reducer() {}
	
	/**
	 * 对tag为NUM的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 */
	public void reduceNUM(ArrayList<Expr> stack) {
		int peekIndex = stack.size() - 1;
		Terminal peek = (Terminal)stack.get(peekIndex);
		ArithExpr arithExpr = new ArithExpr((DecimalToken)peek.token);
		stack.remove(peekIndex);
		stack.add(arithExpr);
		return;
	}
	
	/**
	 * 对tag为NEG的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceNEG(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
		Expr peek = stack.get(peekIndex);
		if (peek.getTag() != Tag.ArithExpr) throw new TypeMismatchedException();
		double v = ((DecimalToken)peek.token).getValue();
		ArithExpr arithExpr = new ArithExpr(-v);
		stack.remove(peekIndex);
		stack.remove(peekIndex -1);
		stack.add(arithExpr);
		return;
	}
	
	/**
	 * 对tag为ADDSUB的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceADDSUB(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Expr a = stack.get(peekIndex - 2);
        Expr o = stack.get(peekIndex - 1);
        Expr b = stack.get(peekIndex);
        if((b.tag == Tag.ADDSUB)) throw new MissingOperandException();
        if(a.getTag() == Tag.BoolExpr) throw new TypeMismatchedException();
        else if(a.getTag() != Tag.ArithExpr)
            throw new MissingOperandException();
        ArithExpr arithExpr;
        if(((OperatorToken)o.token).getValueofString().equals("+"))
            arithExpr = new ArithExpr(((ArithExpr)a).getValue() + ((ArithExpr)b).getValue());
        else
            arithExpr = new ArithExpr(((ArithExpr)a).getValue() - ((ArithExpr)b).getValue());
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.remove(peekIndex - 2);
        stack.add(arithExpr);
        return;
	}
	
	/**
	 * 对tag为AND或OR的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceANDOR(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size()-1;
        Expr a = stack.get(peekIndex-2);
        Expr o = stack.get(peekIndex-1);
        Expr b = stack.get(peekIndex);
        if((b.getTag() == Tag.AND) || (b.getTag() == Tag.OR)) throw new MissingOperandException();
        if(a.getTag() != Tag.BoolExpr|| b.getTag() != Tag.BoolExpr)
            throw new TypeMismatchedException();
        BoolExpr be;
        if(o.getTag() == Tag.AND)
            be=new BoolExpr(((BoolExpr)a).getValue() && ((BoolExpr)b).getValue());
        else
            be=new BoolExpr(((BoolExpr)a).getValue() || ((BoolExpr)b).getValue());
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.remove(peekIndex - 2);
        stack.add(be);	
        return;
	}
	
	/**
	 * 对tag为BOOL的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceBOOL(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Terminal t = (Terminal)stack.get(peekIndex);
        BoolExpr b = new BoolExpr((BooleanToken)t.token);
        stack.remove(peekIndex);
        stack.add(b);
        return;
	}
	
	/**
	 * 对tag为COLON的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceCOLON(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;	// be? ae1 : ae2
        Expr a = stack.get(peekIndex);	// ae2
        Expr b = stack.get(peekIndex - 1);	// :
        Expr c = stack.get(peekIndex - 2);	// ae1
        Expr d = stack.get(peekIndex - 3);	// ?
        Expr e = stack.get(peekIndex - 4);	//be
        
        if(a.getTag() != Tag.ArithExpr || c.getTag() != Tag.ArithExpr || e.getTag() != Tag.BoolExpr)
            throw new TypeMismatchedException();
        if(d.getTag() != Tag.QM)
            throw new exceptions.TrinaryOperationException();
        if(((BoolExpr)e).getValue() == true)
        {
        	//	keep ae1
            stack.remove(peekIndex);
            stack.remove(peekIndex - 1);
            stack.remove(peekIndex - 3);
            stack.remove(peekIndex - 4);
            return;
        }
        else
        {
        	//	keep ae2
            stack.remove(peekIndex - 1);
            stack.remove(peekIndex - 2);
            stack.remove(peekIndex - 3);
            stack.remove(peekIndex - 4);
            return;
        }
	}
	
	/**
	 * 对tag为MULDIV的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceMULDIV(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Expr a =stack.get(peekIndex - 2);
        Expr o = stack.get(peekIndex - 1);
        Expr b = stack.get(peekIndex);
        if((b.getTag() == Tag.MULDIV)) throw new MissingOperandException();
        if(a.getTag() != Tag.ArithExpr || b.getTag() != Tag.ArithExpr)
            throw new TypeMismatchedException();
        ArithExpr ae;
        if(((OperatorToken)o.token).getValueofString().equals("*"))
            ae = new ArithExpr(((ArithExpr)a).getValue() * ((ArithExpr)b).getValue());
        else if(((ArithExpr)b).getValue() == 0)
            throw new DividedByZeroException();
        else
            ae = new ArithExpr(((ArithExpr)a).getValue() / ((ArithExpr)b).getValue());
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.remove(peekIndex - 2);
        stack.add(ae);
        return;
	}
	
	/**
	 * 对tag为NOT的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceNOT(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Expr a=stack.get(peekIndex);
        if(a.getTag() != Tag.BoolExpr) throw new TypeMismatchedException();
        BoolExpr be = new BoolExpr(!((BoolExpr)a).getValue());
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.add(be);
        return;
	}
	
	/**
	 * 对tag为POWER的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reducePower(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Expr a = stack.get(peekIndex - 2);
        Expr o = stack.get(peekIndex - 1);
        Expr b = stack.get(peekIndex);
        if((b.getTag() == Tag.POWER)) throw new MissingOperandException();
        if(a.getTag() != Tag.ArithExpr || b.getTag() != Tag.ArithExpr)
            throw new TypeMismatchedException();
        ArithExpr ae;
        ae = new ArithExpr(Math.pow(((ArithExpr)a).getValue(), ((ArithExpr)b).getValue()));
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.remove(peekIndex - 2);
        stack.add(ae);
        return;
	}
	
	/**
	 * 对tag为RE的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceRE(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
        Expr a = stack.get(peekIndex - 2);
        Expr o = stack.get(peekIndex - 1);
        Expr b = stack.get(peekIndex);
        if(b.getTag() == Tag.RE) throw new MissingOperandException();
        if(a.getTag() != Tag.ArithExpr || b.getTag() != Tag.ArithExpr)
            throw new TypeMismatchedException();
        BoolExpr be = null;
        switch(((OperatorToken)o.token).getValueofString())
        {
            case "=":
                be = new BoolExpr(((ArithExpr)a).getValue() == ((ArithExpr)b).getValue()); 
                break;
            case "<>":
                be=new BoolExpr(((ArithExpr)a).getValue() != ((ArithExpr)b).getValue()); 
                break;
            case "<=":
                be=new BoolExpr(((ArithExpr)a).getValue() <= ((ArithExpr)b).getValue()); 
                break;
            case "<":
                be=new BoolExpr(((ArithExpr)a).getValue() < ((ArithExpr)b).getValue()); 
                break;
            case ">=":
                be=new BoolExpr(((ArithExpr)a).getValue() >= ((ArithExpr)b).getValue()); 
                break;
            case ">":
                be=new BoolExpr(((ArithExpr)a).getValue() > ((ArithExpr)b).getValue()); 
                break;
        }
        stack.remove(peekIndex);
        stack.remove(peekIndex - 1);
        stack.remove(peekIndex - 2);
        stack.add(be);
        return;
	}
	
	/**
	 * 对tag为RP的TopMostTerminal进行reduce
	 * @param stack, 存放token的栈
	 * @throws ExpressionException
	 */
	public void reduceRP(ArrayList<Expr> stack) throws ExpressionException {
		int peekIndex = stack.size() - 1;
		
        Expr a= stack.get(peekIndex);  // )
        Expr o= stack.get(peekIndex - 1); //bool or ae or ael, error if operand
        Expr b= stack.get(peekIndex - 2); // ( or , 
        Expr c= stack.get(peekIndex - 3);//$ or func or ArithExpr
        
        if(o.getTag() ==Tag.RE || o.getTag() ==Tag.COMMA)
        {
            throw new MissingOperandException();
        }
        else if(o.getTag() == Tag.BoolExpr) // (be) to be
        {
            stack.remove(peekIndex);
            stack.remove(peekIndex - 2);
            return;
        }
        else if(o.getTag() == Tag.ArithExprList)
        {
            if(c.getTag() != Tag.ArithExpr) throw new TypeMismatchedException();
            ArithExprList ael=new ArithExprList((ArithExpr)c, (ArithExprList)o);
            Expr d= stack.get(peekIndex - 4);
            Expr f = stack.get(peekIndex - 5);
            ArithExpr ae=null;
            if(d.getTag() == Tag.LP && f.getTag() == Tag.FUNC) // max(ae,ael) to ae
            {
                switch(((OperatorToken)(f.token)).getValueofString())
                {
                    case "max":
                        ae=new ArithExpr(ael.max); break;
                    case "min":
                        ae=new ArithExpr(ael.min); break;
                    default:
                        throw new FunctionCallException();
                }
                stack.remove(peekIndex);  //)
                stack.remove(peekIndex - 1);	//	ael
                stack.remove(peekIndex - 2);	//	,
                stack.remove(peekIndex - 3);	//	ae
                stack.remove(peekIndex - 4);	//	(
                stack.remove(peekIndex - 5);	//	max min
                stack.add(ae);
                return;
            }
            else                          // ae,ael) to ael)
            {
                stack.remove(peekIndex - 1);
                stack.remove(peekIndex - 2);
                stack.set(peekIndex - 3, ael);
                return;
            }
        }
        else if(o.getTag() == Tag.ArithExpr)
        {
            if(b.getTag() == Tag.COMMA) // ae,ae) to ae,ael)
            {
                ArithExprList ael = new ArithExprList((ArithExpr)o);
                stack.set(peekIndex - 1, ael);
                return;
            }
            
            else if(c.getTag() == Tag.FUNC) //sin(ae)
            {
                if(o.getTag() != Tag.ArithExpr) throw new TypeMismatchedException();
                ArithExpr ae=null;
                switch(((OperatorToken)(c.token)).getValueofString())
                {
                    case "sin":
                        ae=new ArithExpr(Math.sin(((ArithExpr)o).getValue())); 
                        break;
                    case "cos":
                        ae=new ArithExpr(Math.cos(((ArithExpr)o).getValue())); 
                        break;
                    default:
                        throw new MissingOperandException();
                }
                stack.remove(peekIndex);
                stack.remove(peekIndex - 1);
                stack.remove(peekIndex - 2);
                stack.remove(peekIndex - 3);
                stack.add(ae);
                return;
            }
            else //(ae) to ae
            {
            	System.out.println(1);
                stack.remove(peekIndex);
                stack.remove(peekIndex - 2);
                return;
            }
        }
        throw new SyntacticException();
	}
}
