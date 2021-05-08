/**
 * 
 */
package scanner;

import exceptions.IllegalDecimalException;
import exceptions.IllegalIdentifierException;
import exceptions.IllegalSymbolException;
import exceptions.LexicalException;
import symbol.Exponent;
import symbol.Fraction;
import symbol.Integral;
import token.*;

/**
 * scanner部分，扫描输入表达式返回token
 * @author Aaron-Qiu
 */
public class Scanner {
	String input;
	int LastTokenTag;
	int index;
	
	/**
	 * 构造函数
	 * @param _input，源表达式
	 */
	public Scanner(String _input) {
		input = _input.toLowerCase();
		LastTokenTag = Tag.START;
		index = 0;
	}
	
	private boolean isEnd () {
		return index >= input.length();
	}
	
	//	找到input从某个数字开始能找到的连续数字串最后一个数字
	private int findLastDigitOfString(int start) {
		int i = start;
		while (Character.isDigit(input.charAt(i))) {
			i++;
			if (i == input.length()) break;
		}
		return i;
	}
	
	/**
	 * 用于与parser交互，由parser调用，返回下一个token
	 * @return 下一个token
	 * @throws LexicalException
	 */
	public Token getNextToken() throws LexicalException
	{
		String head;
		//	去除多余空格，判断是否为空, 是空返回$
		while (true) {
			if (!isEnd()) {
				head = input.substring(index, index + 1);
				if (!head.equals(" ")) break;
				index++;
			} else {
				LastTokenTag = Tag.DOLLAR;
				return new DollarToken();
			}
		}
		
		//	head为数字时
		if (Character.isDigit(head.charAt(0))) {
			//	Decimal Token DFA
			Integral integral = null;
			Fraction fraction = null;
			Exponent exponent = null;
			//	状态1
			int i = findLastDigitOfString(index);
			String intStr = input.substring(index, i);
			integral = new Integral(intStr);
			//	更新index位置以及head
			index = i;	
			if (!isEnd()) {
				head = input.substring(index, index + 1);
			} else {
				LastTokenTag = Tag.NUM;
				return new DecimalToken(integral);
			}
			
			//	状态2-3
			if (head.equals(".")) {
				index++;
				i = findLastDigitOfString(index);
				//	若后面无数字
				if (i == index) {
					throw new IllegalDecimalException();
				}
				intStr = input.substring(index, i);
			    fraction = new Fraction(intStr);
			    //	更新index位置以及head
				index = i;	
				if (!isEnd()) {
					head = input.substring(index, index + 1);
				} else {
					LastTokenTag = Tag.NUM;
					return new DecimalToken(integral,fraction);
				}
			}
			
			//	状态4
			if (head.equalsIgnoreCase("e")) {
				String op = "+";
				index++;
				//	若e后不跟任何东西
				if (!isEnd()) {
					head = input.substring(index, index + 1);
				} else {
					throw new IllegalDecimalException();
				}
				//	是否跳转状态5
				if (!Character.isDigit(head.charAt(0))) {
					if (head.equals("+") || head.equals("-")) {
						op = head.equals("-") ? "-" : "+";
						index++;
					} else {
						throw new IllegalDecimalException();
					}
				}
				i = findLastDigitOfString(index);
				//	若后面无数字
				if (i == index) {
					throw new IllegalDecimalException();
				}
				intStr = input.substring(index, i);
				exponent = new Exponent(new Integral(intStr), op);
				index = i;
			}
			
			if (fraction != null && exponent != null) {
				LastTokenTag = Tag.NUM;
				return new DecimalToken(integral, fraction, exponent);
			} else {
				if (exponent != null) {
					LastTokenTag = Tag.NUM;
					return new DecimalToken(integral, exponent);
				} else if (fraction != null){
					LastTokenTag = Tag.NUM;
					return new DecimalToken(integral, fraction);
				} else {
					LastTokenTag = Tag.NUM;
					return new DecimalToken(integral);
				}
			}
		} else {
			//	head为非数字时		
			//	Boolean Token DFA
			if (head.equalsIgnoreCase("t") || head.equalsIgnoreCase("f")) {
				if (input.substring(index, index + 4).equalsIgnoreCase("true") ) {
					index += 4;
					LastTokenTag = Tag.BOOL;
					return new BooleanToken("true");
				} else if (input.substring(index, index + 5).equalsIgnoreCase("false")) {
					index += 5;
					LastTokenTag = Tag.BOOL;
					return new BooleanToken("false");
				} else {
					throw new IllegalIdentifierException();
				}
			}
			
			//	Operator Token DFA
			if (head.equals("m") || head.equals("s") || head.equals("c")) {
				//	处理3个预定义函数运算
				//	若不足3个字符
				if (index + 3 > input.length()) {
					throw new IllegalIdentifierException();
				}
				String temp = input.substring(index, index + 3);
				if (temp.equals("min") || temp.equals("max") 
						||	temp.equals("sin") || temp.equals("cos")) {
					index += 3;
					LastTokenTag = Tag.FUNC;
					return new OperatorToken(temp, Tag.FUNC);
				} else {
					throw new IllegalIdentifierException();
				}
			} else if (head.equals("-")) {
				index++;
				//	'-'号特殊处理
				if (LastTokenTag == Tag.NUM || LastTokenTag == Tag.RP) {
					LastTokenTag = Tag.ADDSUB;
					return new OperatorToken("-", Tag.ADDSUB);
				} else {
					LastTokenTag = Tag.NEG;
					return new OperatorToken("-", Tag.NEG);
				}
			} else if (head.equals("+")) {
				index++;
				LastTokenTag = Tag.ADDSUB;
				return new OperatorToken("+", Tag.ADDSUB);
			} else if (head.equals("*") || head.equals("/")) {
				index++;
				LastTokenTag = Tag.MULDIV;
				return new OperatorToken(head, Tag.MULDIV);
			} else if (head.equals("=") || head.equals(">") || head.equals("<")) {
				String temp = "";
				// 判断会不会越界
				if (index + 2 <= input.length()) {
					temp = input.substring(index, index + 2);
				}
				
				if (head.equals("=")) {
					index++;
					LastTokenTag = Tag.RE;
					return new OperatorToken("=", Tag.RE);
				} else if (head.equals(">")) {
					if (temp.equals(">=")) {
						index += 2;
						LastTokenTag = Tag.RE;
						return new OperatorToken(">=", Tag.RE);
					} else {
						index++;
						LastTokenTag = Tag.RE;
						return new OperatorToken(">", Tag.RE);
					}
				} else if (head.equals("<")) {
					if (temp.equals("<>")) {
						index += 2;
						LastTokenTag = Tag.RE;
						return new OperatorToken("<>", Tag.RE);
					} if (temp.equals("<=")) {
						index += 2;
						LastTokenTag = Tag.RE;
						return new OperatorToken("<=", Tag.RE);
					} else {
						index++;
						LastTokenTag = Tag.RE;
						return new OperatorToken("<", Tag.RE);
					}
				}
			} else if(head.equals("!")) {
				index++;
				LastTokenTag = Tag.NOT;
				return new OperatorToken("!", Tag.NOT);
			} else if (head.equals("&")) {
				index++;
				LastTokenTag = Tag.AND;
				return new OperatorToken("&", Tag.AND);
			} else if (head.equals("|")) {
				index++;
				LastTokenTag = Tag.OR;
				return new OperatorToken("|", Tag.OR);
			} else if (head.equals("?")) {
				index++;
				LastTokenTag = Tag.QM;
				return new OperatorToken("?", Tag.QM);
			} else if (head.equals(":")) {
				index++;
				LastTokenTag = Tag.COLON;
				return new OperatorToken(":", Tag.COLON);
			} else if (head.equals("(")){
				index++;
				LastTokenTag = Tag.LP;
				return new OperatorToken("(", Tag.LP);
			} else if (head.equals(")")) {
				index++;
				LastTokenTag = Tag.RP;
				return new OperatorToken(")", Tag.RP);
			} else if (head.equals(",")) {
				index++;
				LastTokenTag = Tag.COMMA;
				return new PunctuationToken(",", Tag.COMMA);
			} else if (head.equals("^")) {
				index++;
				LastTokenTag = Tag.POWER;
				return new OperatorToken("^", Tag.POWER);
			} else {
				throw new IllegalSymbolException();
			}
		}		
		//	基本不会到这步
		return new DollarToken();
	}
}
