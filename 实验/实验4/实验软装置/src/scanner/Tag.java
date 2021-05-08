/**
 * 
 */
package scanner;

/**
 * tag类，用于标记符号所属类型
 * @author Aaron-Qiu
 *
 */
public class Tag {
	//	Tag for terminal
    public final static int	
    NUM = 0, BOOL = 1, ADDSUB = 2, MULDIV = 3,
    NEG = 4, POWER = 5, FUNC = 6, LP = 7,
    COMMA = 8,  RP = 9, RE = 10, NOT = 11, 
    AND = 12, OR = 13, QM = 14, COLON = 15, 
    DOLLAR = 16;
    
    //	tag for Nonterminal
    public final static int
    Expr = 300,
    ArithExpr = 301,
    BoolExpr = 302,
    UnaryFunc = 303,
    VariablFunc = 304,
    ArithExprList = 305; 
    
    public final static int
    START = 400,
    NULL = 401;
}
