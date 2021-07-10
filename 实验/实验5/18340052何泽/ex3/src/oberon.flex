import java.io.*;
import exceptions.*;
import java_cup.runtime.*;

%%

%public
%class OberonScanner
%cup
%unicode
%ignorecase
%line 
%column
%yylexthrow LexicalException
%type java_cup.runtime.Symbol
%eofval{
	return symbol(Symbols.EOF);
%eofval}

%{
	int get_line(){
		return yyline;
	}
	int get_column(){
		return yycolumn;
	}
	private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

Integer = [1-9]+[0-9]*|0[0-7]*
IllegalOctal = 0[0-7]*[9|8]+[0-9]*
IllegalInteger 	= {Integer}+{Identifier}+
Identifier = [:jletter:]+[:jletterdigit:]*
Comment = "(*"~"*)"
MismatchedComment= "(*" ([^\*]|"*"+[^\)])*|([^\(]|"("+[^\*])*"*)"
LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t\f]

%%
<YYINITIAL>
{
	"MODULE"				{return symbol(Symbols.MODULE);}
	"PROCEDURE"				{return symbol(Symbols.PROCEDURE);}
	"BEGIN"					{return symbol(Symbols.BEGIN);}
	"END"					{return symbol(Symbols.END);}
	"IF"					{return symbol(Symbols.IF);}
	"THEN"					{return symbol(Symbols.THEN);}
	"ELSIF"					{return symbol(Symbols.ELSIF);}
	"ELSE"					{return symbol(Symbols.ELSE);}
	"WHILE"					{return symbol(Symbols.WHILE);}    
	"DO"					{return symbol(Symbols.DO);}
	"OF"					{return symbol(Symbols.OF);}

	"INTEGER"				{return symbol(Symbols.INTEGER);}
	"BOOLEAN"				{return symbol(Symbols.BOOLEAN);}
	"VAR"					{return symbol(Symbols.VAR);}
	"TYPE"					{return symbol(Symbols.TYPE);}
	"RECORD"				{return symbol(Symbols.RECORD);}
	"CONST"					{return symbol(Symbols.CONST);}
	"ARRAY"					{return symbol(Symbols.ARRAY);}
	"READ"					{return symbol(Symbols.READ);}
	"WRITE"					{return symbol(Symbols.WRITE);}
	"writeln"				{return symbol(Symbols.WRITELN);}

	{Integer}				{
								if (yylength() > 12)
									throw new IllegalIntegerRangeException();
								else
									return symbol(Symbols.NUMBER, yytext());
							}

	"+"						{return symbol(Symbols.PLUS);}
	"-"						{return symbol(Symbols.MINUS);}
	"*"						{return symbol(Symbols.TIMES);}
	"DIV"					{return symbol(Symbols.DIVIDE);}
	"MOD"					{return symbol(Symbols.MOD);}

	"="						{return symbol(Symbols.EQUAL);}
	"#"						{return symbol(Symbols.NOTEQUAL);}
	"<"						{return symbol(Symbols.LT);}
	"<="					{return symbol(Symbols.LE);}
	">"						{return symbol(Symbols.GT);}
	">="					{return symbol(Symbols.GE);}

	"&"						{return symbol(Symbols.AND);}
	"OR"					{return symbol(Symbols.OR);}
	"~"						{return symbol(Symbols.NOT);}

	":="					{return symbol(Symbols.ASSIGN);}
	":"						{return symbol(Symbols.COLON);}
	"("						{return symbol(Symbols.LPAREN);}
	")"						{return symbol(Symbols.RPAREN);}
	"["						{return symbol(Symbols.LBRACKET);}
	"]"						{return symbol(Symbols.RBRACKET);}
	
	{Identifier}			{
								if (yylength()>24)
									throw new IllegalIdentifierLengthException();
								else
									return symbol(Symbols.IDENTIFIER, yytext());
							}

	";"						{return symbol(Symbols.SEMI);}
	","						{return symbol(Symbols.COMMA);}
	"."						{return symbol(Symbols.DOT);}	

	{Comment}				{}
	{WhiteSpace}			{}
	{LineTerminator} 		{}
	{MismatchedComment}		{throw new MismatchedCommentException();}
	{IllegalOctal}			{throw new IllegalOctalException();}
	{IllegalInteger}		{throw new IllegalIntegerException();}
}
.                           {throw new IllegalSymbolException();} 