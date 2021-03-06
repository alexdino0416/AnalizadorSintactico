package Code;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
A=[0-9_]+
E=[ ,\t, \r, \n]+
%{
    private Symbol symbol(int type, Object value){
    	return new Symbol(type, yyline, yycolumn, value);
    }    
    private Symbol symbol(int type){
    	return new Symbol(type, yyline, yycolumn);
    }
%}
%%
if {return new Symbol(sym.If, yychar, yyline, yytext());}
"(" {return new Symbol(sym.AbrePar, yychar, yyline, yytext());}
")" {return new Symbol(sym.CierraPar, yychar, yyline, yytext());}
"==" {return new Symbol(sym.Igual, yychar, yyline, yytext());}
"!=" {return new Symbol(sym.Diferente, yychar, yyline, yytext());}
"<" {return new Symbol(sym.Menor, yychar, yyline, yytext());}
"<=" {return new Symbol(sym.MenorIgual, yychar, yyline, yytext());}
">" {return new Symbol(sym.Mayor, yychar, yyline, yytext());}
">=" {return new Symbol(sym.MayorIgual, yychar, yyline, yytext());}
("(-"{A}")") | {A} {return new Symbol(sym.Numero, yychar, yyline, new Integer(yytext()));}
{E} {/*Ignore*/}
 . {return new Symbol(sym.Error, yychar, yyline, yytext());}