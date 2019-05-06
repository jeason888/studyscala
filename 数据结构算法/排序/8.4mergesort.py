__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
import math
def mergesort(list):
    print(list)
    if (len(list)==1):
        return list
    num=math.ceil(len(list)/2)
    left=mergesort(list[:num])
    right=mergesort(list[num:])
    print("左右分支")
    print(left,right)
    return merge(left,right)
def merge(left,right):
    i,j=0,0
    result=[]
    while i<len(left) and j<len(right):
        if left[i]<right[j]:
            result.append(left[i])
            i+=1
        else:
            result.append(right[j])
            j+=1
    result+=left[i:]
    result+=right[j:]
    return result
if __name__=="__main__":
    print("排序前:")
    list=[8,4,5,7,1,3,6,2]
    print(list)
    print(mergesort(list))
