<h1 align=center>编译原理第六次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

| Step |    Stack    |     Input     |  Reference   | Action |   Output    |
| :--: | :---------: | :-----------: | :----------: | :----: | :---------: |
|  1   |    $\$S$    | $(a,(a,a))\$$ |  $\$\prec($  | shift  |             |
|  2   |    $\$($    | $a,(a,a))\$$  |  $(\prec a$  | shift  |             |
|  3   |   $\$(a$    |  $,(a,a))\$$  |  $a\succ ,$  | reduce |  $S \to a$  |
|  4   |   $\$(S$    |  $,(a,a))\$$  | $S \succ ,$  | reduce |  $L \to S$  |
|  5   |   $\$(L$    |  $,(a,a))\$$  | $( \prec ,$  | shift  |             |
|  6   |  $\$(L,)$   |  $(a,a))\$$   | $, \prec ($  | shift  |             |
|  7   |  $\$(L,($   |   $a,a))\$$   | $( \prec a)$ | shift  |             |
|  8   |  $\$(L,(a$  |   $,a))\$$    | $a \succ ,$  | reduce |  $S \to a$  |
|  9   |  $\$(L,(S$  |   $,a))\$$    | $S \succ ,$  | reduce | $S \succ ,$ |
|  10  |  $\$(L,(L$  |   $,a))\$$    | $( \prec ,$  | shift  |             |
|  11  | $\$(L,(L,$  |    $a))\$$    | $, \prec a$  | shift  |             |
|  12  | $\$(L,(L,a$ |    $))\$$     | $a \succ )$  | reduce |  $S \to a$  |
|  13  | $\$(L,(L,S$ |    $))\$$     | $, \succ )$  | reduce | $L\to L,S$  |
|  14  |  $\$(L,(L$  |    $))\$$     |  $(\equiv)$  | shift  |             |
|  15  | $\$(L,(L)$  |     $)\$$     | $) \succ )$  | reduce | $S\to (L)$  |
|  16  |  $\$(L,S$   |     $)\$$     | $, \succ )$  | reduce | $L\to L,S$  |
|  17  |   $\$(L$    |     $)\$$     |  $(\equiv)$  | shift  |             |
|  18  |   $\$(L)$   |     $\$$      | $) \succ \$$ | reduce | $S\to (L)$  |
|  19  |    $\$S$    |     $\$$      |              | accept |             |