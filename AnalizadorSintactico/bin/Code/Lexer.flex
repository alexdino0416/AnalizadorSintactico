package Code;
import static Code.Tokens.*;
%%
%class Lexer
%type Tokens
A=[0-9_]+
E=[ ,\t, \r, \n]+
%{
    public String lexeme;
%}
%%
if {lexeme=yytext(); return If;}
"(" {lexeme=yytext(); return AbrePar;}
")" {lexeme=yytext(); return CierraPar;}
"==" {lexeme=yytext(); return Igual;}
"!=" {lexeme=yytext(); return Diferente;}
"<" {lexeme=yytext(); return Menor;}
"<=" {lexeme=yytext(); return MenorIgual;}
">" {lexeme=yytext(); return Mayor;}
">=" {lexeme=yytext(); return MayorIgual;}
("(-"{A}+")") | {A}+ {lexeme=yytext(); return Numero;}
{E} {/*Ignore*/}
 . {return Error; }