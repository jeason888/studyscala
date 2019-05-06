__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class Lnode:
    def __init__(self,x):
        self.data=x
        self.next=None
def ConstructList(start):
    i=start
    head = Lnode(None)
    tmp = None
    cur = head
    p=None
    while i < 8:
        tmp = Lnode(None)
        tmp.data = i
        tmp.next = None
        cur.next=tmp
        cur=tmp
        if i==5:
            p=tmp
        i +=1
    return head
def PrintList(head):
    cur = head.next
    while cur is not None:
        print(cur.data)
        cur = cur.next
def DeleteNode(p):
    if p==None or p.next==None:
       return False
    p.data=p.next.data
    tmp=p.next
    p.next=tmp.next
    return True
if __name__=="__main__":
    p=None
    i = 1
    head = Lnode(None)
    tmp = None
    cur = head
    p = None
    while i < 7:
        tmp = Lnode(None)
        tmp.data = i
        tmp.next = None
        cur.next = tmp
        cur = tmp
        if i == 5:
            p = tmp
        i += 1
    PrintList(head)
    result=DeleteNode(p)
    print(result)
    print("\n删除节点后：")
    if result:
       PrintList(head)