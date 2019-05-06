__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
def selectsort(list):
    count=len(list)
    for i in range(0,count):
        min=i
        for j in range(i+1,count):
            if list[min]>list[j]:
                min=j
        list[min],list[i]=list[i],list[min]
        print(list)
    return list

def selectsort2(list):
    for i in range(0,len(list)):
        for j in range(i+1,len(list)):
            if list[i]>list[j]:
                list[i],list[j]=list[j],list[i]
        print(list)
if __name__=="__main__":
    list=[38,65,97,76,13,27,49]
    print("排序前:")
    print(list)
    print("排序步骤：")
    l=selectsort(list)
    print("排序后:")
    print(list)