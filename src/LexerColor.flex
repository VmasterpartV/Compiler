import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
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
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

/* Identificador */
RX{Identificador} { /*Ignorar*/ }

/* Tipos de dato */
I-Var | S-Var | Ch-Var { return textColor(yychar, yylength(), new Color(148, 58, 173)); }

/* Número entero */
12{Numero}12 { return textColor(yychar, yylength(), new Color(35, 120, 147)); }

/* Número real */
12{Numero}12.{Numero} { return textColor(yychar, yylength(), new Color(35, 120, 147)); }

/* Operadores de agrupación */
"(" | ")" | "{" | "}" { return textColor(yychar, yylength(), Color.red); }

/* Signos de puntuación */
"," | ";" { return textColor(yychar, yylength(), Color.green); }

/* Operadores lógicos */
"&&" | "||" { return textColor(yychar, yylength(), new Color(48, 63, 159)); }

/* for */
12for12 { return textColor(yychar, yylength(), new Color(198, 40, 40)); }

. { /* Ignorar */ }