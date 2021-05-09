package parser;

import decimal.Exponent;
import decimal.Fraction;
import decimal.Integral;
import exceptions.IllegalDecimalException;
import exceptions.IllegalIdentifierException;
import exceptions.IllegalSymbolException;
import exceptions.LexicalException;
import token.*;

/**
 * 划分token
 */
public class Scanner {
    private final String input;
    private int lastTokenTag;
    private int index;

    /**
     * 构造函数
     *
     * @param input 表达式
     */
    public Scanner(String input) {
        this.input = input.toLowerCase();
        lastTokenTag = kingOfChar.START;
        index = 0;
    }

    /**
     * Gets next token.
     *
     * @return the next token
     * @throws LexicalException the lexical exception
     */
    public Token getNextToken() throws LexicalException {
        String head;
        while (true) {
            if (!isEnd()) {
                head = input.substring(index, index + 1);
                if (!head.equals(" ")) break;
                index++;
            } else {
                lastTokenTag = kingOfChar.DOLLAR;
                return new DollarToken();
            }
        }
        if (Character.isDigit(head.charAt(0))) {
            Integral integral = null;
            Fraction fraction = null;
            Exponent exponent = null;
            int i = findLastDigitOfString(index);
            String intStr = input.substring(index, i);
            integral = new Integral(intStr);
            index = i;
            if (!isEnd()) {
                head = input.substring(index, index + 1);
            } else {
                lastTokenTag = kingOfChar.NUM;
                return new DecimalToken(integral);
            }

            if (head.equals(".")) {
                index++;
                i = findLastDigitOfString(index);
                if (i == index) {
                    throw new IllegalDecimalException();
                }
                intStr = input.substring(index, i);
                fraction = new Fraction(intStr);
                index = i;
                if (!isEnd()) {
                    head = input.substring(index, index + 1);
                } else {
                    lastTokenTag = kingOfChar.NUM;
                    return new DecimalToken(integral, fraction);
                }
            }

            if (head.equalsIgnoreCase("e")) {
                String op = "+";
                index++;
                if (!isEnd()) {
                    head = input.substring(index, index + 1);
                } else {
                    throw new IllegalDecimalException();
                }
                if (!Character.isDigit(head.charAt(0))) {
                    if (head.equals("+") || head.equals("-")) {
                        op = head.equals("-") ? "-" : "+";
                        index++;
                    } else {
                        throw new IllegalDecimalException();
                    }
                }
                i = findLastDigitOfString(index);
                if (i == index) {
                    throw new IllegalDecimalException();
                }
                intStr = input.substring(index, i);
                exponent = new Exponent(new Integral(intStr), op);
                index = i;
            }

            if (fraction != null && exponent != null) {
                lastTokenTag = kingOfChar.NUM;
                return new DecimalToken(integral, fraction, exponent);
            } else {
                if (exponent != null) {
                    lastTokenTag = kingOfChar.NUM;
                    return new DecimalToken(integral, exponent);
                } else if (fraction != null) {
                    lastTokenTag = kingOfChar.NUM;
                    return new DecimalToken(integral, fraction);
                } else {
                    lastTokenTag = kingOfChar.NUM;
                    return new DecimalToken(integral);
                }
            }
        } else {
            if (head.equalsIgnoreCase("t") || head.equalsIgnoreCase("f")) {
                if (input.substring(index, index + 4).equalsIgnoreCase("true")) {
                    index += 4;
                    lastTokenTag = kingOfChar.BOOL;
                    return new BooleanToken("true");
                } else if (input.substring(index, index + 5).equalsIgnoreCase("false")) {
                    index += 5;
                    lastTokenTag = kingOfChar.BOOL;
                    return new BooleanToken("false");
                } else {
                    throw new IllegalIdentifierException();
                }
            }

            if (head.equals("m") || head.equals("s") || head.equals("c")) {
                if (index + 3 > input.length()) {
                    throw new IllegalIdentifierException();
                }
                String temp = input.substring(index, index + 3);
                if (temp.equals("min") || temp.equals("max") || temp.equals("sin") || temp.equals("cos")) {
                    index += 3;
                    lastTokenTag = kingOfChar.FUNC;
                    return new OperatorToken(temp, kingOfChar.FUNC);
                } else {
                    throw new IllegalIdentifierException();
                }
            } else if (head.equals("-")) {
                index++;
                if (lastTokenTag == kingOfChar.NUM || lastTokenTag == kingOfChar.RP) {
                    lastTokenTag = kingOfChar.ADDSUB;
                    return new OperatorToken("-", kingOfChar.ADDSUB);
                } else {
                    lastTokenTag = kingOfChar.NEG;
                    return new OperatorToken("-", kingOfChar.NEG);
                }
            } else if (head.equals("+")) {
                index++;
                lastTokenTag = kingOfChar.ADDSUB;
                return new OperatorToken("+", kingOfChar.ADDSUB);
            } else if (head.equals("*") || head.equals("/")) {
                index++;
                lastTokenTag = kingOfChar.MULDIV;
                return new OperatorToken(head, kingOfChar.MULDIV);
            } else if (head.equals("=") || head.equals(">") || head.equals("<")) {
                String temp = "";
                if (index + 2 <= input.length()) {
                    temp = input.substring(index, index + 2);
                }
                if (head.equals("=")) {
                    index++;
                    lastTokenTag = kingOfChar.RE;
                    return new OperatorToken("=", kingOfChar.RE);
                } else if (head.equals(">")) {
                    if (temp.equals(">=")) {
                        index += 2;
                        lastTokenTag = kingOfChar.RE;
                        return new OperatorToken(">=", kingOfChar.RE);
                    } else {
                        index++;
                        lastTokenTag = kingOfChar.RE;
                        return new OperatorToken(">", kingOfChar.RE);
                    }
                } else if (head.equals("<")) {
                    if (temp.equals("<>")) {
                        index += 2;
                        lastTokenTag = kingOfChar.RE;
                        return new OperatorToken("<>", kingOfChar.RE);
                    }
                    if (temp.equals("<=")) {
                        index += 2;
                        lastTokenTag = kingOfChar.RE;
                        return new OperatorToken("<=", kingOfChar.RE);
                    } else {
                        index++;
                        lastTokenTag = kingOfChar.RE;
                        return new OperatorToken("<", kingOfChar.RE);
                    }
                }
            } else if (head.equals("!")) {
                index++;
                lastTokenTag = kingOfChar.NOT;
                return new OperatorToken("!", kingOfChar.NOT);
            } else if (head.equals("&")) {
                index++;
                lastTokenTag = kingOfChar.AND;
                return new OperatorToken("&", kingOfChar.AND);
            } else if (head.equals("|")) {
                index++;
                lastTokenTag = kingOfChar.OR;
                return new OperatorToken("|", kingOfChar.OR);
            } else if (head.equals("?")) {
                index++;
                lastTokenTag = kingOfChar.QM;
                return new OperatorToken("?", kingOfChar.QM);
            } else if (head.equals(":")) {
                index++;
                lastTokenTag = kingOfChar.COLON;
                return new OperatorToken(":", kingOfChar.COLON);
            } else if (head.equals("(")) {
                index++;
                lastTokenTag = kingOfChar.LP;
                return new OperatorToken("(", kingOfChar.LP);
            } else if (head.equals(")")) {
                index++;
                lastTokenTag = kingOfChar.RP;
                return new OperatorToken(")", kingOfChar.RP);
            } else if (head.equals(",")) {
                index++;
                lastTokenTag = kingOfChar.COMMA;
                return new PunctuationToken(",", kingOfChar.COMMA);
            } else if (head.equals("^")) {
                index++;
                lastTokenTag = kingOfChar.POWER;
                return new OperatorToken("^", kingOfChar.POWER);
            } else {
                throw new IllegalSymbolException();
            }
        }
        return new DollarToken();
    }

    private boolean isEnd() {
        return index >= input.length();
    }

    private int findLastDigitOfString(int start) {
        int i = start;
        while (Character.isDigit(input.charAt(i))) {
            i++;
            if (i == input.length())
                break;
        }
        return i;
    }

    public static class kingOfChar {
        public final static int NUM = 0, BOOL = 1, ADDSUB = 2, MULDIV = 3, NEG = 4, POWER = 5, FUNC = 6,
                LP = 7, COMMA = 8, RP = 9, RE = 10, NOT = 11, AND = 12, OR = 13, QM = 14, COLON = 15, DOLLAR = 16;
        public final static int Expr = 17, ArithExpr = 18, BoolExpr = 19, ArithExprList = 20;
        public final static int START = 21, NULL = 22;
    }
}
