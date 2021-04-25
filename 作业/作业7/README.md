<h1 align=center>编译原理第七次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

## Exercise 7.1

> Consider the grammar 
>
> ​		S → **(** SR | **a**
>
> ​		R → **,** SR | **)**
>
> Try to construct an SLR(1) parsing table for the grammar, and see if there are conflicts in the parsing table.

<img src="图片/IMG_0A95A61F22AA-1.jpeg" alt="IMG_0A95A61F22AA-1" style="zoom:50%;" />

<img src="图片/IMG_1334.jpg" alt="IMG_1334" style="zoom:50%;" />

<img src="图片/IMG_C03C47514BB9-1.jpeg" alt="IMG_C03C47514BB9-1" style="zoom:50%;" />

没有冲突

## Exercise 7.2

> Consider the grammar 
>
> ​		S → S**a b** | **a** R
>
> ​		R→S|**a**
>
> Is the grammar an SLR(1) grammar? and why?

<img src="图片/IMG_81FFCAE23199-1.jpeg" alt="IMG_81FFCAE23199-1" style="zoom:50%;" />

## Exercise 7.3

> Consider the grammar
>
> ​		 S→A
>
> ​		A→BA|$\epsilon$
>
> ​		B→**a**B|**b**
>
> - Prove that the grammar is an LR(1) grammar.
> - Construct an LR(1) parsing table for the grammar.
> - Show the detailed parsing procedure for the sentence **abab**, following the style in slides of this lecture.

(1)

<img src="图片/IMG_19A79CA7A7F4-1.jpeg" alt="IMG_19A79CA7A7F4-1" style="zoom:50%;" />

<img src="图片/IMG_CB29688C2331-1.jpeg" alt="IMG_CB29688C2331-1" style="zoom:50%;" />

(2)

<img src="图片/IMG_371BAB6A5353-1.jpeg" alt="IMG_371BAB6A5353-1" style="zoom:50%;" />

(3)

<img src="图片/IMG_63358A672EEC-1.jpeg" alt="IMG_63358A672EEC-1" style="zoom:50%;" />

## Exercise 7.4

> Show that the grammar
>
> ​		S → A**a** | **b**A**c** | **d c** | **b d a**
>
> ​		A→**d** 
>
> is LALR(1) but not SLR(1).

<img src="图片/IMG_38C9A01E9386-1.jpeg" alt="IMG_38C9A01E9386-1" style="zoom:80%;" />

<img src="图片/IMG_CB4F63B15AA4-1.jpeg" alt="IMG_CB4F63B15AA4-1" style="zoom: 33%;" />

并无冲突，可以看出是LALR(1)的，但是I~7~ 下一个符号是a，但是FOLLOW(A)={a,c}，于是产生移入规约冲突，所以不是SLR(1)。

