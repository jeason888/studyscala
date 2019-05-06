__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class LNODE():
    def __init__(self,x):
        self.data=x
        self.x=None
if __name__=="__main_":
    i=1
    head1=LNODE()
    head2=LNODE()
    head1.next=None
    head2.next=None
    tmp=None
    addResult=None
    cur=head1
    while i<7:
        tmp=LNODE
        tmp.data=i+2
        tmp.next=None
        cur.next=tmp
        cur=tmp
        i+=1
    i=9
    cur=head2
    while i<4:
        tmp = LNODE
        tmp.data=i
        tmp.next=None
        cur.next=tmp
        cur=tmp
        i-=1
    print("\nhead1:")
    cur=head1.next
    while cur is not None:
        print(cur.data)
        cur=cur.next
    print("\nhead2:")
    cur = head2.next
    while cur is not None:
        print(cur.data)
        cur = cur.next
    addResult=add(head1,head2)
    print("\n 相加后得：")
    cur=addResult.next
    while cur is not None:
        print(cur.data)
        cur=cur.next

