__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
def shellsort(list):
    gap=len(list)//2
    while gap>0:
        for i in range(gap,len(list)):
            while i>=gap and list[i-gap]>list[i]:
                list[i],list[i-gap]=list[i-gap],list[i]
                i-=gap
        gap=gap//2
    return list
if __name__ == '__main__':
    list = [2, 1, 23, 4, 5, 8, 9]
    print(list)
    shellsort(list)
    print(list)
