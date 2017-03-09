package com.github.rhencke.intellij_pony;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.rhencke.intellij_pony.psi.PonyTokenTypes.*;
%%

%{
    public PonyLexer() {
        this((java.io.Reader)null);
    }

    private int nestedCommentDepth = 0;
%}

%class PonyLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

ID
    = ({LETTER} | "_") ({LETTER} | {DIGIT} | "_" | "'")*

INT
    = {DIGIT} ({DIGIT} | "_")*
    | "0x" ({HEX} | "_")+
    | "0b" ({BINARY} | "_")+
    | "'" {CHAR_CHAR}* "'"

FLOAT
    = {DIGIT} ({DIGIT} | "_")* ("." {DIGIT} ({DIGIT} | "_")*)? {EXP}?

STRING
    = "\"" {STRING_CHAR}* "\""
    | "\"\"\"" (("\"" | "\"\"")? [^\"])* "\"\"\"" "\""*

LPAREN_NEW
    = {NEWLINE} "("

LSQUARE_NEW
    = {NEWLINE} "["

MINUS_NEW
    = {NEWLINE} "-"

MINUS_TILDE_NEW
    = {NEWLINE} "-~"

LINECOMMENT
    = "//" [^\n]*

NESTEDCOMMENT_START
    = "/*"

NESTEDCOMMENT_BODY
    = "/" [^*]
    | [^*/]
    | ("*"+ [^*/])

NESTEDCOMMENT_END
    = "*"+ "/"

WS
    = (" " | "\t" | "\r")+

NEWLINE
    = "\n" (" " | "\t" | "\r")*

// fragment
CHAR_CHAR
    = "\\" "'" | {ESC}
    | [^'\\]

STRING_CHAR
    = "\\" "\"" | {ESC}
    | [^\"\\]

EXP
    = ("e" | "E") ("+" | "-")? ({DIGIT} |  "_")+

LETTER
    = [a-z] | [A-Z]

BINARY
    = [0-1]

DIGIT
    = [0-9]

HEX
    = {DIGIT} | [a-f] | [A-F]

ESC
    = "\\" ("a" | "b" | "e" | "f" | "n" | "r" | "t" | "v" | "\\" | "0")
    | {HEX_ESC}
    | {UNICODE_ESC}
    | {UNICODE2_ESC}

HEX_ESC
    = "\\" "x" {HEX} {HEX}

UNICODE_ESC
    = "\\" "u" {HEX} {HEX} {HEX} {HEX}

UNICODE2_ESC
    = "\\" "U" {HEX} {HEX} {HEX} {HEX} {HEX} {HEX}

%state YYNESTEDCOMMENT
%%

<YYINITIAL> {
    {WS}                     { return WHITE_SPACE; }
    {NEWLINE}                { return WHITE_SPACE; }
    {LINECOMMENT}            { return LINE_COMMENT.getElement(); }
    {NESTEDCOMMENT_START}    { yybegin(YYNESTEDCOMMENT); nestedCommentDepth++; }

    "actor"                  { return ACTOR.getElement(); }
    "addressof"              { return ADDRESSOF.getElement(); }
    "and"                    { return AND.getElement(); }
    "as"                     { return AS.getElement(); }
    "be"                     { return BE.getElement(); }
    "box"                    { return BOX.getElement(); }
    "break"                  { return BREAK.getElement(); }
    "class"                  { return CLASS.getElement(); }
    "compile_error"          { return COMPILE_ERROR.getElement(); }
    "compile_intrinsic"      { return COMPILE_INTRINSIC.getElement(); }
    "consume"                { return CONSUME.getElement(); }
    "continue"               { return CONTINUE.getElement(); }
    "digestof"               { return DIGESTOF.getElement(); }
    "do"                     { return DO.getElement(); }
    "else"                   { return ELSE.getElement(); }
    "elseif"                 { return ELSEIF.getElement(); }
    "embed"                  { return EMBED.getElement(); }
    "end"                    { return END.getElement(); }
    "error"                  { return ERROR.getElement(); }
    "false"                  { return FALSE.getElement(); }
    "for"                    { return FOR.getElement(); }
    "fun"                    { return FUN.getElement(); }
    "if"                     { return IF.getElement(); }
    "ifdef"                  { return IFDEF.getElement(); }
    "in"                     { return IN.getElement(); }
    "interface"              { return INTERFACE.getElement(); }
    "is"                     { return IS.getElement(); }
    "isnt"                   { return ISNT.getElement(); }
    "iso"                    { return ISO.getElement(); }
    "lambda"                 { return LAMBDA.getElement(); }
    "let"                    { return LET.getElement(); }
    "__loc"                  { return LOC.getElement(); }
    "match"                  { return MATCH.getElement(); }
    "new"                    { return NEW.getElement(); }
    "not"                    { return NOT.getElement(); }
    "object"                 { return OBJECT.getElement(); }
    "or"                     { return OR.getElement(); }
    "primitive"              { return PRIMITIVE.getElement(); }
    "recover"                { return RECOVER.getElement(); }
    "ref"                    { return REF.getElement(); }
    "repeat"                 { return REPEAT.getElement(); }
    "return"                 { return RETURN.getElement(); }
    "struct"                 { return STRUCT.getElement(); }
    "tag"                    { return TAG.getElement(); }
    "then"                   { return THEN.getElement(); }
    "this"                   { return THIS.getElement(); }
    "trait"                  { return TRAIT.getElement(); }
    "trn"                    { return TRN.getElement(); }
    "true"                   { return TRUE.getElement(); }
    "try"                    { return TRY.getElement(); }
    "type"                   { return TYPE.getElement(); }
    "until"                  { return UNTIL.getElement(); }
    "use"                    { return USE.getElement(); }
    "val"                    { return VAL.getElement(); }
    "var"                    { return VAR.getElement(); }
    "where"                  { return WHERE.getElement(); }
    "while"                  { return WHILE.getElement(); }
    "with"                   { return WITH.getElement(); }
    "xor"                    { return XOR.getElement(); }
    "&"                      { return AMPERSAND.getElement(); }
    "\\\\"                   { return BSLASH.getElement(); }
    "^"                      { return CARAT.getElement(); }
    "@"                      { return CINNAMONROLL.getElement(); }
    ":"                      { return COLON.getElement(); }
    ","                      { return COMMA.getElement(); }
    "."                      { return DOT.getElement(); }
    "..."                    { return DOT_DOT_DOT.getElement(); }
    ".>"                     { return DOT_GT.getElement(); }
    "="                      { return EQ.getElement(); }
    "=="                     { return EQ_EQ.getElement(); }
    "==~"                    { return EQ_EQ_TILDE.getElement(); }
    "!"                      { return EXCL.getElement(); }
    "!="                     { return EXCL_EQ.getElement(); }
    "!=~"                    { return EXCL_EQ_TILDE.getElement(); }
    "/"                      { return FSLASH.getElement(); }
    "/~"                     { return FSLASH_TILDE.getElement(); }
    ">"                      { return GT.getElement(); }
    ">="                     { return GT_EQ.getElement(); }
    ">=~"                    { return GT_EQ_TILDE.getElement(); }
    ">>"                     { return GT_GT.getElement(); }
    ">>~"                    { return GT_GT_TILDE.getElement(); }
    ">~"                     { return GT_TILDE.getElement(); }
    "["                      { return LBRAK.getElement(); }
    "{"                      { return LCURLY.getElement(); }
    "("                      { return LPAREN.getElement(); }
    "<"                      { return LT.getElement(); }
    "<="                     { return LT_EQ.getElement(); }
    "<=~"                    { return LT_EQ_TILDE.getElement(); }
    "<<"                     { return LT_LT.getElement(); }
    "<<~"                    { return LT_LT_TILDE.getElement(); }
    "<~"                     { return LT_TILDE.getElement(); }
    "-"                      { return MINUS.getElement(); }
    "-~"                     { return MINUS_TILDE.getElement(); }
    "%"                      { return PERCENT.getElement(); }
    "%~"                     { return PERCENT_TILDE.getElement(); }
    "|"                      { return PIPE.getElement(); }
    "+"                      { return PLUS.getElement(); }
    "+~"                     { return PLUS_TILDE.getElement(); }
    "]"                      { return RBRAK.getElement(); }
    "}"                      { return RCURLY.getElement(); }
    ")"                      { return RPAREN.getElement(); }
    ";"                      { return SEMI.getElement(); }
    "*"                      { return STAR.getElement(); }
    "*~"                     { return STAR_TILDE.getElement(); }
    "=>"                     { return STRONG_ROCKET.getElement(); }
    "~"                      { return TILDE.getElement(); }
    "?"                      { return WAT.getElement(); }
    "->"                     { return WEAK_ROCKET.getElement(); }
    "#alias"                 { return POUND_ALIAS.getElement(); }
    "#any"                   { return POUND_ANY.getElement(); }
    "#read"                  { return POUND_READ.getElement(); }
    "#send"                  { return POUND_SEND.getElement(); }
    "#share"                 { return POUND_SHARE.getElement(); }
    "#"                      { return POUND.getElement(); }
    {ID}                     { return ID.getElement(); }
    {INT}                    { return INT.getElement(); }
    {FLOAT}                  { return FLOAT.getElement(); }
    {STRING}                 { return STRING.getElement(); }
    {LPAREN_NEW}             { return LPAREN_NEW.getElement(); }
    {LSQUARE_NEW}            { return LSQUARE_NEW.getElement(); }
    {MINUS_NEW}              { return MINUS_NEW.getElement(); }
    {MINUS_TILDE_NEW}        { return MINUS_TILDE_NEW.getElement(); }
}

<YYNESTEDCOMMENT> {
    {NESTEDCOMMENT_START}    { nestedCommentDepth++; }
    {NESTEDCOMMENT_BODY}     { }
    {NESTEDCOMMENT_END}      { nestedCommentDepth--; if(nestedCommentDepth == 0) { yybegin(YYINITIAL); return BLOCK_COMMENT.getElement(); } }
}

[^] { return BAD_CHARACTER; }