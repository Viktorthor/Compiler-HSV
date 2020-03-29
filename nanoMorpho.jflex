%%

%public
%class NanoMorphoLexer
%byaccj
%line
%column
%unicode

%{

public NanoMorphoParser yyparser;

public int getLine() { return yyline+1; }
public int getColumn() { return yycolumn+1; }

public NanoMorphoLexer(java.io.Reader r, NanoMorphoParser yyparser )
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
_DELIM=[(){},;=]
_AND=&&
_OR=\|\|
_NAME=([:letter:]|\_|{_DIGIT})+
_OPNAME=[\+\-*/!%&=><\:\^\~&|?]+

%%

{_DELIM} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return yycharat(0);
}

{_AND} {
    yyparser.yylval = new NanoMorphoParserVal(yytext());
    return NanoMorphoParser.AND;
}

{_OR} {
    yyparser.yylval = new NanoMorphoParserVal(yytext());
    return NanoMorphoParser.OR;
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.LITERAL;
}

"if" {
	return NanoMorphoParser.IF;
}

"elsif" {
	return NanoMorphoParser.ELSIF;
}

"else" {
	return NanoMorphoParser.ELSE;
}

"while" {
	return NanoMorphoParser.WHILE;
}

"return" {
	return NanoMorphoParser.RETURN;
}

"var" {
	return NanoMorphoParser.VAR;
}

{_NAME} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.NAME;
}

{_OPNAME} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	switch( yytext().charAt(0) )
	{
	case '^':
	case '?':
	case '~':
		return NanoMorphoParser.OP1;
	case ':':
		return NanoMorphoParser.OP2;
	case '|':
		return NanoMorphoParser.OP3;
	case '&':
		return NanoMorphoParser.OP4;
	case '!':
	case '=':
	case '<':
	case '>':
		return NanoMorphoParser.OP5;
	case '+':
	case '-':
		return NanoMorphoParser.OP6;
	case '*':
	case '/':
	case '%':
		return NanoMorphoParser.OP7;
	default:
		throw new Error("Invalid opname");
	}
}

";;;".*$ {
}

[ \t\r\n\f] {
}

. {
	return NanoMorphoParser.YYERRCODE;
}
