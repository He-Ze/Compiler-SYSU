import java.io.*;
import exceptions.*;

%%

%public
%class OberonScanner
%unicode
%ignorecase
%line 
%column
%yylexthrow LexicalException
%type String
%eofval{
	return "EOF";
%eofval}

ReservedWord = "module"|"procedure"|"begin"|"end"|"if"|"then"|"elsif"|"else"|"while"|"do"|"of"
Keyword = "integer"|"boolean"|"var"|"type"|"record"|"const"|"array"|"read"|"write"|"writeln"
Integer = [1-9]+[0-9]* | 0[0-7]*
IllegalOctal = 0[0-7]*[9|8]+[0-9]*
IllegalInteger 	= {Integer}+{Identifier}+
Operator = "+"|"-"|"*"|"div"|"mod"|"="|"#"|"<"|"<="|">"|">="|"&"|"or"|"~"|":="|":"|"("|")"|"["|"]"
Identifier = [:jletter:]+[:jletterdigit:]*
Punctuation = ";"|","|"."
Comment = "(*"~"*)"
MismatchedComment= "(*" ([^\*]|"*"+[^\)])*|([^\(]|"("+[^\*])*"*)"
WhiteSpace 	= " "|\r|\n|\r\n|\t

%%

<YYINITIAL>
{
	{ReservedWord}						{return "ReservedWord";}
	{Keyword}							{return "Keyword";}
	{Integer}							{
											if (yylength() > 12)
												throw new IllegalIntegerRangeException();
											else
												return "Integer";
										}
	{IllegalOctal}						{throw new IllegalOctalException();}
	{IllegalInteger}					{throw new IllegalIntegerException();}
	{Operator}							{return "Operator";}
	{Identifier}						{
											if (yylength() > 24)
												throw new IllegalIdentifierLengthException();
											else
												return "Identifier";
										}
	{Punctuation}						{return "Punctuation";}
	{Comment}							{return "Comment";}
	{MismatchedComment}					{throw new MismatchedCommentException();}
	{WhiteSpace}						{}
	.                              		{ throw new IllegalSymbolException(); } 
}