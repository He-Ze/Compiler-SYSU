<h1 align=center>实验四、手工编写递归下降预测分析程序</h1>

<h1 align=center>18340052  何泽</h1>

## 实验步骤 4.1、设计 Oberon-0 语言的翻译模式

$$
\begin{align}
module\ \ \longrightarrow \ \ \ \ &``MODULE"\ identifier_1\ \ ``;"\\
&declaration\\
&``BEGIN"\\ &\ \ \ \ \ \ \ \ statement\_sequence\\
&``END"\ identifier_2\ \ ``."\\
declaration\ \ \longrightarrow \ \ \ \ &const\_declare\ \ \ \ type\_declare\ \ \ \ var\_declare\ \ \ \ procedure\_declaration\\
\\const\_declare\ \ \longrightarrow \ \ \ \ &``CONST"\ const\_list\ |\ \epsilon\\
const\_list\ \ \longrightarrow \ \ \ \ &identifier\ ``="\ expression\ ``;"\ const\_list\ |\ \epsilon\\ \\
type\_declare\ \ \longrightarrow \ \ \ \ &``TYPE"\ type\_list\ |\ \epsilon\\
type\_list\ \ \longrightarrow \ \ \ \ &identifier\ ``="\ type\ ``;"\ type\_list\ |\ \epsilon\\\\
var\_declare\ \ \longrightarrow \ \ \ \ &``VAR"\ var\_list\ |\ \epsilon\\
var\_list\ \ \longrightarrow \ \ \ \ &identifier\_list\ ``="\ type\ ``;"\ var\_list\ |\ \epsilon\\\\
procedure\_declaration\ \ \longrightarrow \ \ \ \ &procedure\_heading\ ``;"\\\ &procedure\_body\ ``;"\\&procedure\_declaration\ |\ \epsilon\\
procedure\_body\ \ \longrightarrow \ \ \ \ &declaration\ \ procedure\_begin\ \ ``END"\ \ identifier\\
procedure\_begin\ \ \longrightarrow \ \ \ \ &``BEGIN"\ statement\_sequence\ |\ \epsilon\\
procedure\_heading\ \ \longrightarrow \ \ \ \ &``PROCEDURE"\ \ identifier\ \ formal\_parameters\\ \\
formal\_parameters\ \ \longrightarrow \ \ \ \ &``("\ \ fp\_section\ \ ``)"\ \ |\ \epsilon\\
fp\_section\ \ \longrightarrow \ \ \ \ &var\_if\ \ identifier\_list\ \ ``:"\ \ type\ |\ var\_if\ \ id\_list\ \ ``:"\ \ type\ ``;"\ fp\_section\\
var\_if\ \ \longrightarrow \ \ \ \ &``VAR"\ |\ \epsilon\\
type\ \ \longrightarrow \ \ \ \ &identifier\ |\ array\_type\ |\ record\_type\ |\ ``INTEGER"\ |\ ``BOOLEAN"\\
array\_type\ \ \longrightarrow \ \ \ \ &``ARRAY"\ \ expression\ \ ``OF" \ \ type\\
record\_type\ \ \longrightarrow \ \ \ \ &``RECORD"\ \ field\_list\ \ ``END"\\
field\_list\ \ \longrightarrow \ \ \ \ &field\_one\ |\ field\_one\ \ ``;"\ \ field\_list\\
field\_one\ \ \longrightarrow \ \ \ \ &identifier\_list\ \ ``:"\ \ type\ |\ \epsilon\\\\
identifier\_list\ \ \longrightarrow \ \ \ \ &identifier\ |\ identifier\ ``;"\ identifier\_list\\
statement\_sequence\ \ \longrightarrow \ \ \ \ &statement\ |\ statement\ ``;"\ statement\_sequence\\
statement\ \ \longrightarrow \ \ \ \ &assignment\ |\ procedure\_call\ |\ if\_statement\ |\ while\_statement\ |\ read\_write\ |\ \epsilon\\\\
while\_statement\ \ \longrightarrow \ \ \ \ &``WHILE" \ \ expression\ \ ``DO" \ \ statement\_sequence\ \ ``END"\\\\
if\_statement\ \ \longrightarrow \ \ \ \ &``IF" \ expression\ \ ``THEN" \ \ statement\_sequence\ \ elsif\_statement \ \ else\_statement\\
elsif\_statement\ \ \longrightarrow \ \ \ \ &``ELSIF" \ expression\ \ ``THEN" \ \ statement\_sequence\ \ elsif\_statement\ |\ \epsilon\\
else\_statement \ \longrightarrow \ \ \ \ &``ELSE" \ \ statement\_sequence\ \ ``END"\ |\ \epsilon\\\\
\end{align}
$$

$$
\begin{align}
read\_write\ \longrightarrow \ \ \ \ &``READ"\ \ ``LPAREN"\ \ identifier\ \ "RPAREN"\\&|``WRITE"\ \ ``LPAREN"\ \ identifier\ \ "RPAREN"\\&|``WRITELN"\ \ ``LPAREN"\ \ identifier\ \ "RPAREN"\\&|``WRITELN"\ \ ``LPAREN"\ \ "RPAREN"\\\\
procedure\_call\ \longrightarrow \ \ \ \ &identifier\ \ actual\_parameters\\
actual\_parameters\ \longrightarrow \ \ \ \ &``("\ ``)"\ |\ ``("\ parameters\_list\ ``)"\ |\ \epsilon\\
parameters\_list\ \longrightarrow \ \ \ \ &expression\ |\ expression\ ``,"\ \ parameters\_list\\
assignment\ \longrightarrow \ \ \ \ &identifier\ \ selector\ \ ``:="\ \ expression\\
expression\ \longrightarrow \ \ \ \ &simple\_expression1\ \ compare\_operation\ \ simple\_expression2\\
compare\_operation\ \longrightarrow \ \ \ \ &``="\ |\ ``\#"\ |\ ``<"\ |\ ``>"\ |\ ``<="\ |\ ``>="\\
simple\_expression\ \longrightarrow \ \ \ \ &term\ |\ term\ \ cal\_operation1\ \ simple\_expression\\
cal\_operation1\ \longrightarrow \ \ \ \ &``+"\ |\ ``-"\ |\ ``OR"\\
term\ \longrightarrow \ \ \ \ &factor\ |\ factor\ \ cal\_operation2\ \ simple\_expression\\
cal\_operation2\ \longrightarrow \ \ \ \ &``*"\ |\ ``DIV"\ |\ ``MOD" |\ ``\&"\\
factor\ \longrightarrow \ \ \ \ &identifier\ |\ identifier \ \ selector\ |\ NUMBER\ |\ ``("\ \ expression\ \ ``)"\ |\ ``\sim"\ factor\\
selector\ \longrightarrow \ \ \ \ &``."\ identifier\ |\ ``["\ \ expression\ \ ``]"\\
identifier\ \longrightarrow \ \ \ \ &letter\ \ identifier\_list\\
identifier\_list\ \longrightarrow \ \ \ \ &letter\ \ identifier\_list\ |\ digit\ \ identifier\_list\ |\ \epsilon
\end{align}
$$





> 由于这个期末事情较多而接下来的任务又比较复杂，很遗憾我实在是没有时间和精力继续完成接下来的任务了，在这里向老师和助教表达歉意。
