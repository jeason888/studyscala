__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class LNode:
    def __init__(self,x):
        self.data=x
        self.next=None
def removeDup(head):
    if head==None or head.next==None:
        return
    outerCur=head.next
    innerCur=None
    innerPre=None
    while outerCur!=None:
        innerCur=outerCur.next
        innerPre=outerCur
        while innerCur!=None:
            if outerCur.data==innerCur.data:
                innerPre.next=innerCur.next
                innerCur=innerCur.next
            else:
                innerPre=innerCur
                innerCur=innerCur.next
        outerCur=outerCur.next
if __name__=="__main__":
    i = 1
    head = LNode(None)
    cur = head
    while i <7:
        tmp = LNode(i)
        if i%2==0:
            tmp.data=i+1
        elif i%3==0:
            tmp.data=i-2
        else:
            tmp.data=i
        tmp.next=None
        cur.next=tmp
        cur=tmp
        i += 1
    cur = head.next
    print("删除前:")
    while cur != None:
        print(cur.data)
        cur = cur.next
    print("\n删除后:")
    removeDup(head)
    cur=head.next
    while cur != None:
        print(cur.data)
        cur = cur.next