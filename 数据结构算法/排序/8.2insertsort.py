__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
def insertsort(list):
    for i in range(1,len(list)):
        key=list[i]
        j=i-1
        while j>=0:
            if list[j]>key:
                list[j+1]=list[j]
                list[j]=key
            j-=1
        print(list)
    return list
if __name__=="__main__":
    list=[38,65,97,76,13,27,49]
    print("排序前:")
    print(list)
    print("排序步骤：")
    l=insertsort(list)
    print("排序后:")
    print(list)
