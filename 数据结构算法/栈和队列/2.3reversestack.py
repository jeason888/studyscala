__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class Stack:
    def __init__(self):
        self.items=[]
    def empty(self):
        return len(self.items)==0
    def size(self):
        return len(self.items)
    #返回栈顶元素
    def peek(self):
        if not self.empty():
            return self.items[len(self.items)-1]
        else:
            return None
    def pop(self):
        if len(self.items)>0:
            return self.items.pop()
        else:
            print("栈已空")
            return None
    def push(self,item):
        self.items.append(item)
def reverse_stack(s):
    if s.empty():
        return
    moveBottomToTop(s)
    top=s.peek()
    reverse_stack(s)
    s.push(top)
def moveBottomToTop(s):
    if s.empty():
        return
    top1=s.peek()
    s.pop()
    if not s.empty():
        moveBottomToTop(s)
        top2=s.peek()
        s.pop()
        s.push(top1)
        s.push(top2)
    else:
        s.push(top1)
if __name__=="__main__":
    s=Stack()
    s.push(5)
    s.push(4)
    s.push(3)
    s.push(2)
    s.push(1)
    reverse_stack(s)
    print("翻转后出栈的顺序为:")
    while not s.empty():
        print(s.peek())
        s.pop()