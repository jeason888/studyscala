__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
from numpy import *;#导入numpy的库函数
import numpy as np; #这个方式使用numpy的函数时，需要以np.开头。
a1=mat([[1,1,1],[2,3,3],[4,2,5]])
a4=a1[1,:]
print(a4)