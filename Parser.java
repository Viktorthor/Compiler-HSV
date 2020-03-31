//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 10 "nanoMorpho.byaccj"
	import java.io.*;
	import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short LITERAL=257;
public final static short NAME=258;
public final static short OP1=259;
public final static short OP2=260;
public final static short OP3=261;
public final static short OP4=262;
public final static short OP5=263;
public final static short OP6=264;
public final static short OP7=265;
public final static short AND=266;
public final static short OR=267;
public final static short VAR=268;
public final static short IF=269;
public final static short ELSIF=270;
public final static short ELSE=271;
public final static short WHILE=272;
public final static short RETURN=273;
public final static short OPNAME=274;
public final static short UNOP=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,   16,    2,   14,   14,   14,   17,   17,
    3,    3,    4,    4,    4,   11,   11,   12,   12,   13,
   13,    5,    5,    5,    5,    5,    5,    5,    5,    5,
    6,    6,    6,    6,    6,    6,    8,    8,    8,    9,
    9,    9,   10,    7,    7,    7,   15,   15,   15,   15,
   15,   15,   15,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    9,    0,    3,    1,    0,    3,
    3,    2,    2,    3,    1,    3,    1,    3,    1,    2,
    1,    3,    3,    3,    3,    3,    3,    3,    2,    1,
    4,    1,    1,    3,    7,    8,    0,    1,    1,    8,
    8,    7,    4,    0,    3,    1,    1,    1,    1,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,    3,    0,    2,    0,    0,    8,    0,    0,
    0,    0,    7,    0,    0,    0,   33,    0,   47,   48,
   49,   50,   51,   52,   53,    0,    0,    0,    0,    0,
    0,    0,    0,   30,   15,    0,    0,    0,   10,    0,
    0,    0,    0,   13,    0,    0,   20,    5,    0,   12,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   29,
   14,   46,    0,    0,    0,   34,   11,    0,    0,    0,
    0,    0,    0,   28,   16,   18,   31,    0,    0,    0,
   45,    0,    0,    0,    0,    0,   35,    0,    0,   36,
   38,   39,    0,    0,    0,    0,    0,   43,    0,    0,
    0,   40,   41,
};
final static short yydgoto[] = {                          1,
    2,    3,   31,   32,   33,   34,   63,   90,   91,   92,
   35,   36,   37,    9,   38,    4,   15,
};
final static short yysindex[] = {                         0,
    0,    0,    0, -252,    0,  -24, -250,    0,  -30,  -56,
 -214, -197,    0, -250,   54,  -31,    0,  -38,    0,    0,
    0,    0,    0,    0,    0,   29,   32,   54,   54,   89,
  -33,   14, -164,    0,    0, -191, -188,  -35,    0,   54,
   54,   54,   54,    0,   38,   40,    0,    0,   22,    0,
  106,  106,  106,  106,  106,  106,  -35,   89,   89,    0,
    0,    0,   -7,   41,   43,    0,    0, -206, -206, -213,
 -199, -218, -180,    0,    0,    0,    0,   54,  -34,  -21,
    0,   54,   54,  -16,    3, -209,    0,   48,  -20,    0,
    0,    0,   54,   54,   50,   20,  -17,    0,   54,   37,
 -209,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    1,    0,    0,    0,    0,   -6,    0,    0,    0,
    0,   71,    0,  -18,    0,    0,    0,  120,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    0,    0,  -19,  -32,    0,    0,    0,
   -2,    0,    0,    0,    0,  120,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  214,  246,  210,
  174,  158,  148,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  129,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  139,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   91,  -79,  -10,   61,  -28,    0,    0,    6,    7,
   47,   51,   88,  105,    0,    0,    0,
};
final static int YYTABLESIZE=513;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
    1,   41,   84,   85,   29,    6,   29,    8,   19,   60,
   10,   19,   11,   11,   96,    7,   30,   44,   45,  100,
   49,   17,   40,   29,   17,    6,   19,   39,   74,   61,
   62,   64,   65,   77,    6,   30,   78,    6,   44,   17,
    6,   44,   29,   13,   21,   56,   57,   21,   54,   55,
   56,   57,   30,   52,   53,   54,   55,   56,   57,   29,
   88,   89,   21,   55,   56,   57,   12,   81,   42,   30,
   14,   43,   50,   49,   49,   58,   29,   59,   66,   41,
   67,   79,   95,   80,   57,   49,   30,   93,   82,   49,
   97,   48,    5,   29,   51,   52,   53,   54,   55,   56,
   57,   83,   94,    9,   75,   99,  102,  103,   86,   76,
    9,   68,   69,   70,   71,   72,   73,   47,   16,    0,
    0,   30,    0,    0,    0,    0,    0,   87,   29,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   98,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   32,  101,    0,   32,    0,    0,    0,    0,    0,   37,
    0,    0,   37,    0,    0,    0,    0,    0,   32,   42,
    0,    0,   42,    0,    0,    0,    0,   37,   27,    0,
    0,   27,    0,    0,    0,    0,    0,   42,   26,    0,
    0,   26,    0,    0,    0,    0,   27,    0,    0,    0,
    0,    0,    0,    0,   25,    0,   26,   25,    0,    0,
    0,   17,   46,   17,   18,   19,   20,   21,   22,   23,
   24,   25,   25,   26,   19,   26,   27,    0,   27,   28,
   17,   18,   19,   20,   21,   22,   23,   24,   25,    0,
   24,    0,   26,   24,   22,   27,   28,   22,    4,   17,
   18,   19,   20,   21,   22,   23,   24,   25,   24,   21,
   21,   26,   22,    0,   27,   28,   17,   18,   19,   20,
   21,   22,   23,   24,   25,    0,   23,    0,   26,   23,
    0,   27,   28,   17,   18,   19,   20,   21,   22,   23,
   24,   25,    0,    0,   23,   26,    0,    0,   27,   28,
   17,   18,   19,   20,   21,   22,   23,   24,   25,    0,
    0,    0,   26,    0,    0,   27,   28,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    0,    0,    0,    9,
    0,    0,    9,    9,    0,   17,   46,   19,   20,   21,
   22,   23,   24,   25,    0,    0,    0,   26,    0,    0,
   27,    0,   17,   46,   19,   20,   21,   22,   23,   24,
   25,    0,    0,    0,   26,    0,    0,   27,   32,   32,
   32,   32,   32,   32,   32,   32,   32,   37,   37,   37,
   37,   37,   37,   37,   37,   37,    0,   42,   42,   42,
   42,   42,   42,   42,   42,   42,   27,   27,   27,   27,
   27,   27,    0,   27,   27,    0,   26,   26,   26,   26,
   26,    0,    0,   26,   26,    0,    0,    0,    0,    0,
    0,    0,   25,   25,   25,   25,    0,    0,    0,   25,
   25,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   24,   24,
   24,    0,   22,    0,    0,   24,   24,    0,    0,   22,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   23,    0,    0,    0,    0,    0,
    0,   23,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   40,   82,   83,   40,  258,   40,  258,   41,   38,
   41,   44,   44,   44,   94,   40,   33,   28,   29,   99,
   31,   41,   61,   40,   44,   44,   59,   59,   57,   40,
   41,   42,   43,   41,   41,   33,   44,   44,   41,   59,
   59,   44,   40,  258,   41,  264,  265,   44,  262,  263,
  264,  265,   33,  260,  261,  262,  263,  264,  265,   40,
  270,  271,   59,  263,  264,  265,  123,   78,   40,   33,
  268,   40,   59,   84,   85,  267,   40,  266,   41,   40,
   59,   41,   93,   41,  265,   96,   33,   40,  123,  100,
   41,  125,    2,   40,  259,  260,  261,  262,  263,  264,
  265,  123,  123,   33,   58,  123,  101,  101,  125,   59,
   40,   51,   52,   53,   54,   55,   56,   30,   14,   -1,
   -1,   33,   -1,   -1,   -1,   -1,   -1,  125,   40,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  125,   40,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   41,  125,   -1,   44,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   59,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   59,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   59,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   59,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   59,   44,   -1,   -1,
   -1,  257,  258,  257,  258,  259,  260,  261,  262,  263,
  264,  265,   59,  269,  267,  269,  272,   -1,  272,  273,
  257,  258,  259,  260,  261,  262,  263,  264,  265,   -1,
   41,   -1,  269,   44,   41,  272,  273,   44,  258,  257,
  258,  259,  260,  261,  262,  263,  264,  265,   59,  266,
  267,  269,   59,   -1,  272,  273,  257,  258,  259,  260,
  261,  262,  263,  264,  265,   -1,   41,   -1,  269,   44,
   -1,  272,  273,  257,  258,  259,  260,  261,  262,  263,
  264,  265,   -1,   -1,   59,  269,   -1,   -1,  272,  273,
  257,  258,  259,  260,  261,  262,  263,  264,  265,   -1,
   -1,   -1,  269,   -1,   -1,  272,  273,  257,  258,  259,
  260,  261,  262,  263,  264,  265,   -1,   -1,   -1,  269,
   -1,   -1,  272,  273,   -1,  257,  258,  259,  260,  261,
  262,  263,  264,  265,   -1,   -1,   -1,  269,   -1,   -1,
  272,   -1,  257,  258,  259,  260,  261,  262,  263,  264,
  265,   -1,   -1,   -1,  269,   -1,   -1,  272,  259,  260,
  261,  262,  263,  264,  265,  266,  267,  259,  260,  261,
  262,  263,  264,  265,  266,  267,   -1,  259,  260,  261,
  262,  263,  264,  265,  266,  267,  259,  260,  261,  262,
  263,  264,   -1,  266,  267,   -1,  259,  260,  261,  262,
  263,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,  260,  261,  262,   -1,   -1,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,  260,
  261,   -1,  259,   -1,   -1,  266,  267,   -1,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  259,   -1,   -1,   -1,   -1,   -1,
   -1,  266,  267,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'",null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"LITERAL","NAME","OP1","OP2","OP3","OP4",
"OP5","OP6","OP7","AND","OR","VAR","IF","ELSIF","ELSE","WHILE","RETURN",
"OPNAME","UNOP",
};
final static String yyrule[] = {
"$accept : start",
"start : program",
"program : program function",
"program : function",
"$$1 :",
"function : $$1 NAME '(' argsfun ')' '{' variables exprs '}'",
"argsfun :",
"argsfun : argsfun ',' NAME",
"argsfun : NAME",
"variables :",
"variables : VAR argsfun ';'",
"exprs : exprs exp ';'",
"exprs : exp ';'",
"exp : RETURN exp",
"exp : NAME '=' exp",
"exp : orexp",
"orexp : andexp OR orexp",
"orexp : andexp",
"andexp : notexp AND andexp",
"andexp : notexp",
"notexp : '!' notexp",
"notexp : binopexp",
"binopexp : binopexp OP1 binopexp",
"binopexp : binopexp OP2 binopexp",
"binopexp : binopexp OP3 binopexp",
"binopexp : binopexp OP4 binopexp",
"binopexp : binopexp OP5 binopexp",
"binopexp : binopexp OP6 binopexp",
"binopexp : binopexp OP7 smallexp",
"binopexp : op smallexp",
"binopexp : smallexp",
"smallexp : NAME '(' commasepExp ')'",
"smallexp : NAME",
"smallexp : LITERAL",
"smallexp : '(' exp ')'",
"smallexp : WHILE '(' exp ')' '{' exprs '}'",
"smallexp : IF '(' exp ')' '{' exprs '}' elseiforelse",
"elseiforelse :",
"elseiforelse : elseifsent",
"elseiforelse : elsesent",
"elseifsent : ELSIF '(' exp ')' '{' exprs '}' elseifsent",
"elseifsent : ELSIF '(' exp ')' '{' exprs '}' elsesent",
"elseifsent : ELSIF '(' exp ')' '{' exprs '}'",
"elsesent : ELSE '{' exprs '}'",
"commasepExp :",
"commasepExp : commasepExp ',' exp",
"commasepExp : exp",
"op : OP1",
"op : OP2",
"op : OP3",
"op : OP4",
"op : OP5",
"op : OP6",
"op : OP7",
};

//#line 169 "nanoMorpho.byaccj"

private Lexer lexer;
private static int varAmount;
private static HashMap<String,Integer> varTable;
static private String name;
int last_token_read;

private void addVar( String name )
{
	if( varTable.get(name) != null )
	{
		yyerror("Variable "+name+" already exists");
	}
	varTable.put(name,varAmount++);
}

private int findVar( String name )
{
	Integer res = varTable.get(name);
	if( res == null )
	{
		yyerror("Unidentified variable " + name);
	}
	return res;
}

private int yylex()
{
	int yyl_return = -1;
	try
	{
		yylval = null;
		last_token_read = yyl_return = lexer.yylex();
		if( yylval==null )
			yylval = new ParserVal(Parser.yyname[yyl_return]);
	}
	catch (IOException e)
	{
		System.err.println("IO error: "+e);
	}
	return yyl_return;
}

public void yyerror( String message )
{
	System.err.println("Error: " + message);
	System.exit(1);
}

public Parser(Reader r)
{
	lexer = new Lexer(r, this);
}

public static void main( String args[] ) throws IOException
{
	Parser yyparser = new Parser(new FileReader(args[0]));
	name = args[0].substring(0,args[0].lastIndexOf('.'));
	yyparser.yyparse();
}


/*--------------------------------------------------------------
  ------------------- PROGRAM GENERATOR HERE--------------------
  --------------------------------------------------------------*/

   // Constants that represent the assembler functions to call.
    public final static String EXPRESSION_RETURN = "RETURN";
    public final static String EXPRESSION_STORE = "STORE";
    public final static String EXPRESSION_CALL = "CALL";
    public final static String EXPRESSION_FETCH = "FETCH";
    public final static String EXPRESSION_LITERAL = "LITERAL";
    public final static String EXPRESSION_IF = "IF";
    public final static String EXPRESSION_WHILE = "WHILE";
    public final static String EXPRESSION_BODY = "BODY";

    private static int nextLabel = 1;


	private static void emit(String line )
	{
		System.out.println(line);
    }

	public static int newLabel()
	{
		return nextLabel++;
    }

    public void generateProgram(String filename, Object[] funs)
    {
		emit("\"formula.mexe\" = main in\n{{");
        for(int i = 0; i < funs.length; i++)
        {
			generateFunction((Object[])funs[i]);
        }
		emit("}}\n*BASIS;");
    }

    public void generateFunction(Object[] f)
    {
        String fname = (String)f[0];
        int argCount = (Integer)f[1];
        int varAmount = (Integer)f[2];

        emit("#\""+fname+"[f"+argCount+"]\" =");
        emit("[");

        if(varAmount > 0 )
        {
            emit("(MakeVal null)");
            for(int i = 0; i < varAmount; i++)
            {
                emit("(Push)");
            }
        }

        for (Object exp : (Object[])f[3])
        {
           generateExpr((Object[])exp);
        }
        emit("(Return)");
      	emit("];");
    }

    public void generateExpr(Object[] exp)
    {
           switch ((String)((Object[])exp)[0])
           {
              case EXPRESSION_RETURN:
                generateExprP((Object[])((Object[])exp)[1]);
                emit("(Return)");
                break;
              case EXPRESSION_CALL:
                Object[] args = (Object[])((Object[])exp)[2];
                int i;
                for(i=0; i != args.length; i++)
                {
                    if(i==0)
                    {
                        generateExpr((Object[])args[i]);
                    }
                    else
                    {
                        generateExprP((Object[])args[i]);
                    }
                }
                emit("(Call #\""+(String)((Object[])exp)[1]+"[f"+i+"]\" "+i+")");
                break;
              case EXPRESSION_FETCH:
                emit("(Fetch "+(int)((Object[])exp)[1]+")");
                break;
              case EXPRESSION_LITERAL:
                emit("(MakeVal "+(String)((Object[])exp)[1]+")");
                break;
              case EXPRESSION_IF:
                int labElse = newLabel();
                int labEnd = newLabel();
                generateJump(((Object[])(((Object[])exp)[1])),0,labElse);
                generateExpr(((Object[])(((Object[])exp)[2])));
                emit("(Go _"+labEnd+")");
                emit("_"+labElse+":");

                if(((Object[])(((Object[])exp)[3])) != null)
                {
                    generateExpr(((Object[])(((Object[])exp)[3])));
                }
                emit("_"+labEnd+":");
                break;
              case EXPRESSION_WHILE:
                int labTrue = newLabel();
                int labFalse = newLabel();
                emit("_"+labTrue+":");
                generateJump(((Object[])(((Object[])exp)[1])),0,labFalse);
                generateExpr(((Object[])(((Object[])exp)[2])));
                emit("(Go _"+labTrue+")");
                emit("_"+labFalse+":");
                break;
              case EXPRESSION_STORE:
                generateExpr((Object[])((Object[])exp)[2]);
                emit("(Store "+(int)((Object[])exp)[1]+")");
                break;
              case EXPRESSION_BODY:
                for (Object b_expr : (Object[])exp[1])
                {
                    generateExpr((Object[])b_expr);
                }
                break;
              default:
                throw new Error("Unknown intermediate code type: \""+(String)((Object[])exp)[0]+"\"");
           }
    }

    public void generateExprP(Object[] exp)
    {
        switch ((String)((Object[])exp)[0])
        {
            case EXPRESSION_CALL:
                Object[] args = (Object[])((Object[])exp)[2];
                int i;
                for(i=0; i != args.length; i++)
                {
                    generateExprP((Object[])args[i]);
                }
                if( i==0 )
                {
                    emit("(Push)");
                }
                emit("(Call #\""+(String)((Object[])exp)[1]+"[f"+i+"]\" "+i+")");
                break;
            case EXPRESSION_FETCH:
                emit("(FetchP "+(int)((Object[])exp)[1]+")");
                break;
            case EXPRESSION_LITERAL:
                emit("(MakeValP "+(String)((Object[])exp)[1]+")");
                break;
            default:
                throw new Error("Unknown intermediate code type: \""+(String)((Object[])exp)[0]+"\"");
		}
    }

    public void generateJump(Object[] exr, int labelTrue, int labelFalse )
	{
		switch((String)exr[0])
		{
            case EXPRESSION_LITERAL:
                String literal = (String)exr[1];
                if(literal.equals("false") || literal.equals("null"))
                {
                    if( labelFalse!=0 ) emit("(Go _"+labelFalse+")");
                    return;
                }
                if( labelTrue!=0 ) emit("(Go _"+labelTrue+")");
                return;
        default:
			generateExpr(exr);
			if( labelTrue!=0 ) emit("(GoTrue _"+labelTrue+")");
			if( labelFalse!=0 ) emit("(GoFalse _"+labelFalse+")");
		}
	}
//#line 610 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 34 "nanoMorpho.byaccj"
{ generateProgram(name, ((Vector<Object>)(val_peek(0).obj)).toArray()); }
break;
case 2:
//#line 40 "nanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(1).obj)).add(val_peek(0).obj); yyval.obj=val_peek(1).obj; }
break;
case 3:
//#line 41 "nanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
case 4:
//#line 47 "nanoMorpho.byaccj"
{
			varAmount = 0;
			varTable = new HashMap<String,Integer>();
		}
break;
case 5:
//#line 55 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{val_peek(7).sval, val_peek(5).ival, varAmount, ((Vector<Object>)(val_peek(1).obj)).toArray()}; }
break;
case 6:
//#line 60 "nanoMorpho.byaccj"
{ yyval.ival = 0; }
break;
case 7:
//#line 61 "nanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival = val_peek(2).ival + 1; }
break;
case 8:
//#line 62 "nanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival = 1; }
break;
case 11:
//#line 73 "nanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(2).obj)).add(val_peek(1).obj); yyval.obj=val_peek(2).obj; }
break;
case 12:
//#line 74 "nanoMorpho.byaccj"
{ yyval.obj = new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(1).obj); }
break;
case 13:
//#line 79 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_RETURN, val_peek(0).obj}; }
break;
case 14:
//#line 80 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_STORE, findVar(val_peek(2).sval), val_peek(0).obj}; }
break;
case 15:
//#line 81 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 16:
//#line 86 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval, new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 17:
//#line 87 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 18:
//#line 92 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval, new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 19:
//#line 93 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 20:
//#line 98 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, "!", new Object[]{val_peek(0).obj} }; }
break;
case 21:
//#line 99 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 22:
//#line 103 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 23:
//#line 104 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 24:
//#line 105 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 25:
//#line 106 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 26:
//#line 107 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 27:
//#line 108 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 28:
//#line 109 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval ,new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 29:
//#line 110 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(1).sval , new Object[]{val_peek(0).obj}}; }
break;
case 30:
//#line 111 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 31:
//#line 115 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_CALL, val_peek(3).sval, ((Vector<Object>)(val_peek(1).obj)).toArray()}; }
break;
case 32:
//#line 116 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_FETCH, findVar(val_peek(0).sval)}; }
break;
case 33:
//#line 117 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_LITERAL, val_peek(0).sval}; }
break;
case 34:
//#line 118 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(1).obj; }
break;
case 35:
//#line 122 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_WHILE, val_peek(4).obj, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(1).obj)).toArray()}}; }
break;
case 36:
//#line 127 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_IF, val_peek(5).obj, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(2).obj)).toArray()}, val_peek(0).obj}; }
break;
case 37:
//#line 131 "nanoMorpho.byaccj"
{ yyval.obj = null; }
break;
case 38:
//#line 132 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 39:
//#line 133 "nanoMorpho.byaccj"
{ yyval.obj = val_peek(0).obj; }
break;
case 40:
//#line 141 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_IF, val_peek(5).obj, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(2).obj)).toArray()}, val_peek(0).obj}; }
break;
case 41:
//#line 146 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_IF, val_peek(5).obj, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(2).obj)).toArray()}, val_peek(0).obj}; }
break;
case 42:
//#line 150 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_IF, val_peek(4).obj, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(1).obj)).toArray()}, null}; }
break;
case 43:
//#line 157 "nanoMorpho.byaccj"
{ yyval.obj = new Object[]{EXPRESSION_IF, new Object[]{EXPRESSION_LITERAL, "true"}, new Object[]{EXPRESSION_BODY, ((Vector<Object>)(val_peek(1).obj)).toArray()}, null};}
break;
case 44:
//#line 161 "nanoMorpho.byaccj"
{ yyval.obj = new Vector<Object>(); }
break;
case 45:
//#line 162 "nanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(2).obj)).add(val_peek(0).obj); yyval.obj=val_peek(2).obj; }
break;
case 46:
//#line 163 "nanoMorpho.byaccj"
{ yyval.obj = new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
//#line 938 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################