# 模糊控制总结

1. 智能控制（IC）三元论 -> 人工智能（AI）、运筹学（OR）、自动控制（AC）

2. 智能控制分支：专家系统（Expert System）、神经网络控制（Neural Networks Control）、模糊控制（Fuzzy Control）

3. 权威期刊：IEEE Transaction on Fuzzy Systems（TFS）

4. Zadeh于20C60S系统的提出了模糊逻辑，并引入了模糊集合还有隶属度函数，使得True和False 之间成为连续的过渡态。

5. 隶属度函数： $A(x)$ 中存在集合的 $A(x)=1$，不存在的$A(x)=0$，也有0和1之间的中间值。

    常用类型

    1. 三角形 `A=trimf(x,[a,b,c]);`
    2. 钟形 `A=gbellmf(x,[a,b,c]);`
    3. 高斯型 `A=gaussmf(x,[sigma,c]);`
    4. 梯形 `A=tramf(x,[a,b,c,d]);`
    5. Sigmoid型 `A=sigmf(x,[a,c]);`

6. 模糊集的表示：

    1. Zadeh法：$A=\frac{0}{1}+\frac{0.25}{2}+\frac{0.5}{3}=\frac{0.25}{2}+\frac{0.5}{3}$
    2. 序对法（值，隶属度）：$A={(1,0),(2,0.25),(3,0.5)}$
    3. 向量法：$A=(0~~0.25~~0.5)$
    4. 函数法

7. `matlab`中设置x和y轴的最大最小值`axis([xmin,xmax,ymin,ymax]);`

8. `matlab`中设置图例：`legend('Line 1','Line 2','location','best');`

9. 重要集合

    1. 支集：隶属度大于0的部分$Supp~A = \{x|x\in U, A(X)>0\}$

    2. 核集：隶属度为1的部分 $Ker~A=\{x|x\in U,A(X)=1\}$

    3. 正规模糊集：核集不为空的集合

10. 数积：$\lambda A(x)=\left\{\begin{aligned}
      & A(X) &\lambda\ge A(x)\\
      & \lambda &\lambda<A(X)
      \end{aligned}\right.=\min(A(x),\lambda)=\lambda\wedge A(x)$，相当于削头

11. 凸集 $A\in\R~~~~ x_1<x_2<x_3 ~~~A(x_2)>=\min(A(x_1),A(x_3))$ ，不一定是凸函数

12. 模糊数：当集合A既是正规集又是凸集的时候，A就是一个模糊数。

       1. 图像必须是单峰的
       2. 最大隶属度必须是1

13. 子集：模糊集合的包含：设$A,B$均为$U$上的模糊集，如果有$\forall x\in U$有$A(x)\le B(x)$则称为A包含于B，记为$A\subseteq B$，用隶属度表示

14. 对模糊集合来说，德摩根成立，互补率不再成立（$A\cap A^c\ne\varnothing$）

15. 直积：笛卡尔积：注意集合顺序 $A=\{1,2\}~~~B=\{3,4,5\}~~~ A\times B=\{(1,3),(1,4),(1,5),(2,3),(2,4),(2,5)\}$

16. 二元模糊关系：$R(x,y):A\times B$。三大**基本要素**：元素对，隶属度，方向性

17. 关系合成：$R=P\circ Q$，左取行，右取列，先取小再取大。

     性质：不能交换，可以结合     
     1. $A\circ B\ne B\circ A$
        
     2. $A\circ (B\circ C)=(A\circ B)\circ C$
    
18. 条件命题：
     1. $T(A\rightarrow U)=T(\bar A)\or T(U)$
        
     2. $T((A\and B)\rightarrow U)=T(\bar A)\or T(\bar B)\or T(U)$

19. 模糊条件命题：$R(a,b)=A(a)\rightarrow B(b)=(1-A(a))\or(A(a)\and B(b))\approx A(a)\and B(b)$

20. Mamdani算法公式:$R=\overrightarrow{A}\circ B$，$\overrightarrow{A}$为列向量。

21. Mamdani形模糊控制器有：模糊化、反模糊化和推理模块

22. 三段论：基本MZ模糊推理
     1. 大前提：$A\rightarrow B$，小前提：$A^*$，结论：$B^*$。
     2. 计算方法 $B^*(b)=A^*(a)\circ R(a,b)=A^*(a)\circ (\overrightarrow{A}(a)\circ B(b))$
     3. 简便计算：$B^*=A^*\circ R=A^*\circ (\overrightarrow{A} \circ B）=（A^* \circ \overrightarrow{A})\circ B=\lambda \and B$
     4. $\lambda=\max(\min(A^*,A))$，对大前提和小前提，先取小再取大，得到一个数字。

23. 复合MZ模糊推理
     1. I型：大前提：$A(a)\and B(b)\rightarrow C(c)$，用$A^*(a)\and B^*(b)$求出$C^*(c)$
           1. $D=\overrightarrow{A}\circ B$
           
           2. $D^*=\overrightarrow{A^*}\circ B^*$
           
           3. $F=\min(D^*,D)~~\lambda = (F)_{max}$，对两个D矩阵按位置先取小，再取大
           
           4. $C^*=\lambda \and C$
         
     2. II型：大前提：有$(A_1\rightarrow B_1)\cup (A_2\rightarrow B_2)$，用$A^*$得到$B_1^* ,~ B_2^*$
           1. $F_1=\min(A_1,A^*)~~~\lambda_1=(F_1)_{max}$
           
           2. $F_2=\min(A_2,A^*)~~~\lambda_2=(F_2)_{max}$
           
           3. $B_1^*=\lambda_1 \and B_1~~~,B_2^*=\lambda_2\and B_2$
           
           4. $B^*=B_1\cup B_2$

24. 聚类方法：

     1. 硬聚类：KM（K-means，K均值法聚类）

         1. 聚类中心$c_i$：当前分类时每一类的数据的算术平均值。假设$|H_i|$为聚类$H_i$内点的个数，有聚类中心$c_i=\frac{1}{|H_i|}\underset{x\in H_i}{\sum}x$

         2. 目标函数$J$：聚类的目标是使点之间的欧几里得距离最小。$J=\displaystyle\sum_{i=1}^m\underset{x\in H_i}{\sum}||x-c_i||^2$

         3. 基本思想：求每个组$H_i$的聚类中心$c_i$​，使目标函数达到最小。算法本身就是不断通过迭代的方式，通过优化代价函数$J$的寻找诸聚类中心$c_i$ ，进一步明确各个元素所属的类别。

         4. 迭代方式：

             1. KM是迭代算法，因此首先必须要对聚类中心进行初始化。由于聚类的类别数m已知，因此常用的方法是从中任意选择m个元素作为聚类中心。得到初始的$c_i$和$J$
             2. 每次迭代的时候将离每个点归到距离最近的聚类中心，并更新聚类中心的值。
             3. 两次的目标函数差值小于目标值$\delta$停止。

         5. 隶属度矩阵：每行表示各个种类，每列表示各个点。每个位置的点如果输入当前位置的种类则为1，否则为0（硬聚类）。使用隶属度矩阵的目标函数：$J=\displaystyle\sum_{i=1}^m\displaystyle\sum^n_{j=1}u_{ij}||x_{ij}-c_i||^2$，其中$\displaystyle\sum^m_{i=1}u_{ij}=1$，每个点只能属于或不属于一个集合。

         6. matlab

             ```matlab
             U=kmeans(x,m)  %x是点集，m是类别数
             [U,c]=kmeans(x,m)
             [U,c,sum_dist]=kmeans(x,m)
             [U,c,sum_dist,dist]=kmeans(x,m)
             ```

             

     2. 软聚类：FCM（Fuzzy c-means，模糊C均值聚类）

         1. FCM对正态分布聚类效果最好，对无规律分步的野点最敏感。
         2. 基本上继承了KM算法，但是隶属度的取值变成了$[0,1]$的区间，于是被称为软聚类。
         3. 目标函数稍有变动：$J=\displaystyle\sum_{i=1}^m\displaystyle\sum^n_{j=1}u^\alpha_{ij}||x_{ij}-c_i||^2$，$\displaystyle\sum^m_{i=1}u_{ij}=1$，此处的$\alpha \in (1,+\infin )$​是一个加权参数。本质上就是在隶属度和为$1$的条件下求$J$的最小值。
         4. 拉格朗日目标函数：$J=\displaystyle\sum_{i=1}^m\displaystyle\sum^n_{j=1}u^\alpha_{ij}||x_{ij}-c_i||^2+\displaystyle\sum^n_{j=1}\Big(\displaystyle\sum_{i=1}^m u_{ij}-1 \Big)$引入参数$\lambda$是为了调整聚类的严格程度，越大的$\lambda$会越接近传统的KM。
         5. 聚类中心$c_i$：对$J$求$c_i$的偏导得到$c_i=\frac{\displaystyle\sum^n_{j=1}u_{ij}^\alpha x_j}{\displaystyle \sum^n_{j=1}u^\alpha _{ij}}$
         6. 更新公式$u_{ij}=\frac{1}{\displaystyle\sum^m_{k=1}\Big(\frac{||x_j-c_i||}{||x_j-c_k||}\Big)^\frac{2}{\alpha-1}}$

25. w

