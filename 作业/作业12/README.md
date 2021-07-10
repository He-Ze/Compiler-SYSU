<h1 align=center>编译原理第十二次理论作业</h1>

<h1 align=center>18340052  何泽</h1>

> Given the following flow graph:
>
> <img src="截屏2021-07-04 21.11.31.png" alt="截屏2021-07-04 21.11.31" style="zoom:25%;" />
>
> - Compute the dominators of all nodes.
> - Find all back edges in the flow graph.
> - Find all natural loops defined by each back edge.

### 1. 

D(1)={1}

D(2)={1,2}

D(3)={1,2,3}

D(4)={1,2,3,4}

D(5)={1,2,5}

D(6)={1,2,5,6}

### 2. 

4->3  5->2  

### 3.

{3,4} defined by 4->3  

{2,3,4,5} defined by 5->2