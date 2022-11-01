import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Minuscula = [a-z]
Identificador = {Digito}+{Minuscula}
Caracter = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü0-9"("")""{""}"","";""+""-""*""/""%""<""="">""!""&""|""?""!""'""#""."\"]


/* Número */
Numero = [0-9]+
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/* Identificador */
RX{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Tipos de dato */
I-Var | S-Var | Ch-Var { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* Número entero */
("" | -)12{Numero}12 { return token(yytext(), "I-Var", yyline, yycolumn); }

/* Número real */
("" | -)12{Numero}12.{Numero} { return token(yytext(), "S-Var", yyline, yycolumn); }

/* Cadena */
\" {Letra}+ \" { return token(yytext(), "Ch-Var", yyline, yycolumn); }

/* Separadores */
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn); }
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn); }
"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn); }
"," { return token(yytext(), "COMA", yyline, yycolumn); }
";" { return token(yytext(), "PUNTO_COMA", yyline, yycolumn); }

/* Operadores aritméticos */
"+" | "-" | "*" | "/" | "%" { return token(yytext(), "OP_ARIT", yyline, yycolumn); }

/* Operadores relacionales */
"<" | "<=" | ">" | ">=" | "==" | "!=" { return token(yytext(), "OP_REL", yyline, yycolumn); }

/* Operador de asignación */
"=" { return token(yytext(), "OP_ASIG", yyline, yycolumn); }

/* Operadores lógicos */
"&&" | "||" { return token(yytext(), "OP_LOG", yyline, yycolumn); }

/* for */
12for12 { return token(yytext(), "FOR", yyline, yycolumn); }

/* Errores */
{Caracter}+ { return token(yytext(), "ERROR", yyline, yycolumn); }

. { return token(yytext(), "ERROR", yyline, yycolumn); }