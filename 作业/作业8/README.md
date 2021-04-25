<h1 align=center>编译原理第八次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

> Given the translation scheme for the EQN language (see pp.40 in this lecture), calculate the height and depth of the input:
>
>  **text sub text sub text**.
>
> Suppose that for each **text**,
>
> - getHeight(ps, **text**.lexval) = 8 * ps 
> - getDepth(ps, **text**.lexval) = 0

text1.ps=10  text1.ht=80  text1.dp=0

text2.ps=7  text2.ht=56  text2.dp=0

text3.ps=7  text3.ht=56  text3.dp=0

将前面的text sub text当作B1,则B1.ps=10  B1.ht=max(80,56-2.5)=80,B1.dp=max(0,10*0.25)=2.5

则此时text3为B2，最后的结果B.ps=10  B.ht=max(80,56-2.5)=80  B.dp=max(2.5,0+2.5)=2.5

所以最后的结果是height为80，depth为2.5