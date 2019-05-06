__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
def maopaosort(list):
    for i in range(0,len(list)-1):
        for j in range(0,len(list)-1-i):
            if list[j]>list[j+1]:
                list[j+1],list[j]=list[j],list[j+1]
        print(list)
    return list

if __name__=="__main__":
    list=[38,65,97,76,13,27,49]
    print("排序前:")
    print(list)
    print("排序步骤：")
    l=maopaosort(list)
    print("排序后:")
    print(list)
