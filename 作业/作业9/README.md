<h1 align=center>编译原理第九次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

## Exercise 9.1

>What is the translation result of input token string: **x := A[y, z]**?
>
>- Tips: use the translation scheme for Pascal.
>
>(DBv1, pp.486)

我用了书上的条件：A为$10\times20$的数组，翻译结果为：
$$
\begin{align}
t1&=y*20\\
t1&=t1+z\\
t2&=c\ \ \ \ \ \ \ \ (常数c=base_A-84)\\
t3&=4*t1\\
t4&=t2[t3]\\
x&=t4
\end{align}
$$

## Exercise 9.2

> What is the translation result of input token string: **c + a\[i][j]**?
>
> - Tips: use the translation scheme for C/C++.
>
> (DBv2, pp.385)

$$
\begin{align}
t1&=i*12\\
t2&=j*4\\
t3&=t1+t2\\
t4&=a[t3]\\
t5&=c+t4
\end{align}
$$

## Exercise 9.3

> What is the translation result of input token string: **x < 100 || x > 200 && x != y**?
>
> - Tips: use the translation scheme for boolean expressions with backpatching.
>
> - Suppose that the start position of the generated code is 100.
>
> (DBv2, pp.412-414)

$$
\begin{align}
100&:\ \ if\ x<100\ \ goto\ \_ \\
101&:\ \ goto\ 102\\
102&:\ \ if\ x>200\ \ goto\ \ 104\\
103&:\ \ goto\ \_\\
104&:\ \ if\ x!=y\ \ goto\ \ \_\\
105&:\ \ goto\ \ \_
\end{align}
$$