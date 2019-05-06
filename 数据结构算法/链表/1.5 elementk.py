__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class LNode:
    def __init__(self,x):
        self.data=x
        self.next=None
def Constructlist():
    i=1
    head = LNode(None)
    head.next=None
    tmp=None
    cur=head
    while i<8:
        tmp=LNode(None)
        tmp.data=i
        tmp.next=None
        cur.next=tmp
        cur=tmp
        i+=1
    return head
def PrintList(head):
    cur=head.next
    while cur!=None:
        print(cur.data)
        cur=cur.next
if __name__=="__main__":
    head=Constructlist()
    result=None
    print("链表：")
    PrintList(head)
    # result=FindLastK(head,3)
    # if result!=None:
    #     print("\n 链表倒数第3个元素为:"+str(result.data))