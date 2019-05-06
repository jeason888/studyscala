__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class LNode():
    def __init__(self,x):
        self.data=x
        self.next=None
def Reverse1(head):
    if head==None or head.next==None:
        return
    pre=None
    cur=None
    next=None
    #把链表首节点变为尾节点
    cur=head.next
    next=cur.next
    cur.next=None
    pre=cur
    cur=next
    #使当前遍历到节点cur指向其前驱节点
    while cur.next!=None:
        next=cur.next
        cur.next=pre
        pre=cur
        cur=cur.next
        cur=next
    cur.next=pre
    head.next=cur
def Reverse(head):
    if head==None or head.next==None:
        return
    cur=None
    next=None
    cur=head.next.next
    head.next.next=None
    while cur is not None:
        next=cur.next
        cur.next=head.next
        head.next=cur
        cur=next
if __name__=="__main__":
    i = 1
    head = LNode(None)
    cur = head
    while i <= 8:
        tmp = LNode(i)
        cur.next = tmp
        cur = cur.next
        i += 1
    cur = head.next
    print("逆序前:")
    cur = head.next
    while cur != None:
        print(cur.data)
        cur = cur.next
    cur = head.next
    print("\n逆序后:")
    Reverse(head)
    cur=head.next
    while cur != None:
        print(cur.data)
        cur = cur.next








