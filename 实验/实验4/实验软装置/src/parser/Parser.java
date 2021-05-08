/**
 * 
 */
package parser;

import java.util.ArrayList;

import exceptions.EmptyExpressionException;
import exceptions.ExpressionException;
import exceptions.FunctionCallException;
import exceptions.MissingLeftParenthesisException;
import exceptions.MissingOperandException;
import exceptions.MissingOperatorException;
import exceptions.MissingRightParenthesisException;
import exceptions.TrinaryOperationException;
import exceptions.TypeMismatchedException;
import token.DollarToken;
import scanner.Scanner;
import scanner.Tag;
import expr.ArithExpr;
import expr.BoolExpr;
import expr.Expr;
import expr.Terminal;

/**
 * Parser 用于对token进行操作并生产结果
 * @author HeZe
 *
 */
public class Parser {
	private Scanner scanner;
	private Reducer reducer;
	private ArrayList<Expr> stack;
	private Terminal lookahead;
	
	private void initialize(String input) {
		scanner = new Scanner(input);
		reducer = new Reducer();
		stack = new ArrayList<Expr>();
		stack.add(new Terminal(new DollarToken()));
	}
	
	/**
	 * parser构造函数，需要提供input
	 * @param input 输入计算器的表达式
	 * @throws EmptyExpressionException
	 */
	public Parser(String input) throws EmptyExpressionException {
		if (input.isEmpty())
			throw new EmptyExpressionException();
		initialize(input);
	}
	
	
	private Terminal getTopMostTerminal() {
		int index = stack.size() - 1;
		while (true) {
			Expr temp = stack.get(index);
			if (temp.isTerminal())
				return (Terminal)temp;
			else index--;
		}
	}
	
	private double accept() throws TypeMismatchedException {
		Expr peek = stack.get(stack.size() - 1);
		if (peek.getTag() == Tag.BoolExpr) {
			System.out.println(((BoolExpr)peek).getValue());
			throw new TypeMismatchedException();
		}
		return ((ArithExpr)peek).getValue();
	}
	
	private void reduce() throws ExpressionException {
		Terminal topMostTerminal = getTopMostTerminal();
		switch (topMostTerminal.getTag()) {
		case Tag.NUM:
			reducer.reduceNUM(stack);
			break;
		case Tag.BOOL:
			reducer.reduceBOOL(stack);
			break;
		case Tag.ADDSUB:
			reducer.reduceADDSUB(stack);
			break;
		case Tag.MULDIV:
			reducer.reduceMULDIV(stack);
			break;
		case Tag.NOT:
			reducer.reduceNOT(stack);
			break;
		case Tag.NEG:
			reducer.reduceNEG(stack);
			break;
		case Tag.POWER:
			reducer.reducePower(stack);
			break;
		case Tag.AND: case Tag.OR:
			reducer.reduceANDOR(stack);
			break;
		case Tag.RE:
			reducer.reduceRE(stack);
			break;
		case Tag.COLON:
			reducer.reduceCOLON(stack);
			break;
		case Tag.RP:
			reducer.reduceRP(stack);
			break;
		default:
			System.out.println(topMostTerminal.getTag());
			break;
		}
	}
	
	private void shift() throws ExpressionException{
		stack.add(lookahead);
		lookahead = new Terminal(scanner.getNextToken());
	}
	
	/**
	 * 用于启动parse动作
	 * @return 计算结果
	 * @throws ExpressionException
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
}
