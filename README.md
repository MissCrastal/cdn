# cdn程序文档
标签： version_1.0

---
## 变量声明
$a$：内容服务器部署成本
$m$：网络节点个数
$n（n < m）$：视频服务器节点个数
$k（k < m）$：消费节点个数
$x_{ij}$：第i个消费节点接收第j个服务器的流量
$y_i$：第i个消费节点所需流量
$c_{ij}$：第i个消费节点接收第j个服务器的单位流量所需最少费用（固定数值）


---
## 数学模型

1.消费节点流量来源
$$ 
        \begin{pmatrix}
        y_1 \\
        y_2 \\
        \vdots \\
        y_k  \\
        \end{pmatrix}
        =
        \begin{pmatrix}
        x_{11} & x_{12} & \cdots & x_{1j} \\
        x_{21} & x_{22} & \cdots & x_{2j} \\
        \vdots & \vdots & \ddots & \vdots \\
        x_{n1} & x_{n2} & \cdots & x_{nj} \\
        \end{pmatrix}
        \begin{pmatrix}
        1 \\
        1 \\
        \vdots \\
        1  \\
        \end{pmatrix}
$$

$$
cost=\sum_{i=1}^k\sum_{j=1}^nx_{ij}c_{ij}+na
$$

---
## 待解决问题
求$cost$的最小值
 1. 视频服务器的位置选择：$ C_m^n$ 
 2. 第i个消费节点接收第j个服务器的流量：$ x_{i,j} $

---
## 优化方法
TODO

---



[TOC]