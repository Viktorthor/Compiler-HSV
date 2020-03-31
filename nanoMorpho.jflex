/**
	JFlex lesgreinir fyrir NanoMorpho.
	Höfundar: Högni Freyr Gunnarsson (hfg7), Son Van Nguyen (svn5), Viktor Þór Freysson (vthf1)
 */

%%

%public
%class Lexer
%byaccj

%unicode

%{

public Parser yyparser;

public Lexer(java.io.Reader r, Parser yyparser )
{
	this(r);
	this.yyparser = yyparser;
}

%}

_DIGIT=[0-9]
_FLOAT={_DIGIT}+\.{_DIGIT}+([eE][+-]?{_DIGIT}+)?
_INT={_DIGIT}+
_STRING=\"([^\"\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|\\[0-7][0-7]|\\[0-7])*\"
_CHAR=\'([^\'\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|(\\[0-7][0-7])|(\\[0-7]))\'
_DELIM=[()\{\},;=]
_NAME=([:letter:]|{_DIGIT})+

%%

{_DELIM} {
	yyparser.yylval = new ParserVal(yytext());
	return yycharat(0);
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	yyparser.yylval = new ParserVal(yytext());
	return Parser.LITERAL;
}


"while" {
	return Parser.WHILE;
}

"if" {
	return Parser.IF;
}

"elsif" {
	return Parser.ELSIF;
}

"else" {
	return Parser.ELSE;
}

"var" {
	return Parser.VAR;
}

"return" {
	return Parser.RETURN;
}

{_NAME} {
	yyparser.yylval = new ParserVal(yytext());
	return Parser.NAME;
}

[\+\-*/!%&=><\:\^\~&|?]+ {
	yyparser.yylval = new ParserVal(yytext());
	switch( yytext().charAt(0) )
	{
	case '^':
	case '?':
	case '~':
		return Parser.OP1;
	case ':':
		return Parser.OP2;
	case '|':
		return Parser.OP3;
	case '&':
		return Parser.OP4;
	case '!':
	case '=':
	case '<':
	case '>':
		return Parser.OP5;
	case '+':
	case '-':
		return Parser.OP6;
	case '*':
	case '/':
	case '%':
		return Parser.OP7;
	default:
		throw new Error("Invalid opname");
	}
}

"//".*$ { // Tvö skástrik fyrir comment
}

[ \t\r\n\f] {
}

. {
	return Parser.YYERRCODE;
}
