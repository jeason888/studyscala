__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
import math
def adjustheap(list,root,size):
    lchild=2*root+1
    rchild=2*root+2
    max=root
    if root<size//2:
        if lchild<size and list[lchild]>list[max]:
            max=lchild
        if rchild<size and list[rchild]>list[max]:
            max=rchild
        if max!=root:
            list[max],list[root]=list[root],list[max]
            adjustheap(list,max,size)
def buildheap(list,size):
    for i in range(0,(size//2))[::-1]:
        adjustheap(list,i,size)
def heapsort(list):
    size=len(list)
    buildheap(list,size)
    for i in range(0,size)[::-1]:
        list[0],list[i]=list[i],list[0]
        adjustheap(list,0,i)
def print_tree(array): #打印堆排序使用
    '''
    深度 前空格 元素间空格
    1     7       0
    2     3       7
    3     1       3
    4     0       1
    '''
    # first=[0]
    # first.extend(array)
    # array=first
    index = 1
    depth = math.ceil(math.log2(len(array))) # 因为补0了，不然应该是math.ceil(math.log2(len(array)+1))
    sep = '  '
    for i in range(depth):
        offset = 2 ** i
        print(sep * (2 ** (depth - i - 1) - 1), end='')
        line = array[index:index + offset]
        for j, x in enumerate(line):
            print("{:>{}}".format(x, len(sep)), end='')
            interval = 0 if i == 0 else 2 ** (depth - i) - 1
            if j < len(line) - 1:
                print(sep * interval, end='')
        index += offset
        print()
if __name__ == '__main__':
    list = [8,7,6,5,4,3,2,1]
    print(list)
    print(print_tree(list))
    heapsort(list)
    print(print_tree(list))
    print(list)
