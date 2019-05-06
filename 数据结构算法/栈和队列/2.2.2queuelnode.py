__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class Lnode:
    def __init__(self,x):
        self.data=x
        self.next=None
class MyQueue:
    #分配头结点
    def __init__(self):
        self.pHead=None
        self.pEnd=None
    def empty(self):
        if self.pHead==None:
            return true
        else:
            return False
    #获取栈的元素个数
    def size(self):
        size=0
        p=self.pHead
        while p!=None:
            p=p.next
            size+=1
        return size
    #入队列，把元素e加到队列尾
    def enQueue(self,e):
        p=Lnode(None)
        p.data=e
        p.next=None
        if self.pHead==None:
            self.pHead=self.pEnd=p
        else:
            self.pEnd.next=p
            self.pEnd=p
        #出对列，删除队列元素
    def deQueue(self):
        if self.pHead==None:
            print("出对列失败，对列已经为空")
        self.pHead=self.pHead.next
        if self.pHead==None:
            self.pHead=None
    def getFront(self):
        if self.pHead==None:
            print("获取队首元素失败，对列已空")
            return None
        return self.pHead.data
    def getBack(self):
        if self.pEnd==None:
            print("获取队首元素失败，对列已空")
            return None
        return self.pEnd.data
if __name__=="__main__":
    queue=MyQueue()
    queue.enQueue(1)
    queue.enQueue(2)
    queue.enQueue(2)
    queue.enQueue(2)
    queue.enQueue(2)
    print("队首元素为"+str(queue.getFront()))
    print("队尾元素为" + str(queue.getBack()))
    print("对列大小为"+str(queue.size()))