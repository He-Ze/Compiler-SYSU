<h1 align=center>编译原理第五次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

## Exercise 5.1

> Given the following grammar 
>
> ​		S → **( **L **)** | **a**
>
> ​		L → L **,** S | S
>
> - Construct an LL(1) parsing table for the grammar 
>     - Note: you must eliminate the left recursion first.
>
> - Draw the detailed process of the parsing of the sentence **(a, (a, a))**, follow the style in theprevious slides.

- 消除左递归如下：
    $$
        \begin{aligned}
        S &\to (L)\mid a \\
        L &\to SL' \\
        L' &\to , SL'\mid\epsilon
        \end{aligned}
    $$

然后可求FIRST和FOLLOW：（因为文法中包含逗号，故将文法中的逗号用单引号括起来以示区分）
$$
\begin{array}{rlrl}
	FIRST(S) &= \{(,a \} & FOLLOW(S) &= \{\$,)\}\\
	FIRST(L) &= \{(,a\} & FOLLOW(L) &= \{)\}\\
	FIRST(L') &= \{',',\epsilon\} & FOLLOW(L') &= \{)\}
	\end{array}
$$
故可以得出下表：

|      |     (      |        )        |     a      |      ,      |  $   |
| :--: | :--------: | :-------------: | :--------: | :---------: | :--: |
|  S   | $S\to(L)$  |                 |  $S\to a$  |             |      |
|  L   | $L\to SL'$ |                 | $L\to SL'$ |             |      |
|  L'  |            | $L'\to\epsilon$ |            | $L'\to,SL'$ |      |

- 

|   Matched   |    Stack     |     Input     |         Action         |
| :---------: | :----------: | :-----------: | :--------------------: |
|             |    $S\$$     | $(a,(a,a))\$$ |                        |
|             |   $(L)\$$    | $(a,(a,a))\$$ |   output $S\to (L)$    |
|     $($     |    $L)\$$    | $a,(a,a))\$$  |                        |
|     $($     |   $SL')\$$   | $a,(a,a))\$$  |   output $L\to SL'$    |
|     $($     |   $aL')\$$   | $a,(a,a))\$$  |    output $S\to a$     |
|    $(a$     |   $L')\$$    |  $,(a,a))\$$  |                        |
|    $(a$     |  $,SL')\$$   |  $,(a,a))\$$  |  output $L'\to ,SL'$   |
|    $(a,$    |   $SL')\$$   |  $(a,a))\$$   |                        |
|    $(a,$    |  $(L)L')\$$  |  $(a,a))\$$   |   output $S\to (L)$    |
|   $(a,($    |  $L)L')\$$   |   $a,a))\$$   |                        |
|   $(a,($    | $SL')L')\$$  |   $a,a))\$$   |   output $L\to SL'$    |
|   $(a,($    | $aL')L')\$$  |   $a,a))\$$   |    output $S\to a$     |
|   $(a,(a$   |  $L')L')\$$  |   $,a))\$$    |                        |
|   $(a,(a$   | $,SL')L')\$$ |   $,a))\$$    |   output $L'\to,SL'$   |
|  $(a,(a,$   | $SL')L')\$$  |    $a))\$$    |                        |
|  $(a,(a,$   | $aL')L')\$$  |    $a))\$$    |    output $S\to a$     |
|  $(a,(a,a$  |  $L')L')\$$  |    $))\$$     |                        |
|  $(a,(a,a$  |   $)L')\$$   |    $))\$$     | output $L'\to\epsilon$ |
| $(a,(a,a)$  |   $L')\$$    |     $)\$$     |                        |
| $(a,(a,a)$  |    $)\$$     |     $)\$$     | output $L'\to\epsilon$ |
| $(a,(a,a))$ |     $\$$     |     $\$$      |                        |

## Exercise 5.2

> Given the following grammar 
>
> ​		A → B | B C
>
> ​		B → **a** B | $\epsilon$
>
> ​		C → **a b**
>
> - Left factor the grammar.
>
> - After left factoring, is the grammar an LL(1) grammar? or is it an LL(k) grammar? and why?
>     - Note: you may try the input string **ab**.

- 提取左公因子：

    A → B A'

    B → **a** B | $\epsilon$

    A' → C | $\epsilon$

    C → **a b**

- 

首先，这个语法不是LL(1),因为

$A \to BC \to \epsilon C\to C\to ab$

$A\to B\to aB$

第一个都是a，故不是LL(1)文法

此外，这个语法是LL(k)的。







