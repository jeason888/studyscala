__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class Lnode:
    def __init__(self,x):
        self.data=x
        self.next=None
def ConstructList(start):
    i=start
    head=Lnode(None)
    head.next=None
    tmp=None
    cur=head
    while i<7:
        tmp=Lnode(None)
        tmp.data = i
        tmp.next=None
        cur.next=tmp
        cur=tmp
        i+=2
    return head
def PrintList(head):
    cur=head.next
    while cur is not None:
        print(cur.data)
        cur=cur.next
def Merge(head1,head2):
    if head1==None or head1.next==None:
       return head2
    if head2==None or head2.next==None:
       return head1
    cur1=head1.next
    cur2=head2.next
    head=None
    cur=None
    if cur1.data<cur2.data:
        head=head1
        cur=cur1
        cur1=cur1.next
    else:
        head = head2
        cur =cur2
        cur2 = cur2.next
    while cur1!=None and cur2!=None:
        if cur1.data<cur2.data:
            cur.next=cur1
            cur=cur1
            cur1=cur1.next
        else:
            cur.next = cur2
            cur=cur2
            cur2= cur2.next
    if cur1!=None:
        cur.next=cur1
    else:
        cur.next=cur2
    return head

if __name__=="__main__":
    head1=ConstructList(1)
    print("\nhead1:")
    PrintList(head1)
    head2 = ConstructList(2)
    print("\nhead2:")
    PrintList(head2)
    print("合并后的链表:")
    head=Merge(head1,head2)
    PrintList(head)