__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
class MyStack():
    def __init__(self):
        self.items=[]
    def isEmpty(self):
        return  len(self.items)==0
    def size(self):
        return  len(self.items)
    def top(self):
        if not self.isEmpty():
            return self.items[len(self.items)-1]
    def push(self,item):
         return self.items.append(item)
    def pop(self):
        if len(self.items)>0:
            return self.items.pop()
        else:
            print("栈已空")
            return None
if __name__=="__main__":
    s=MyStack()
    s.push(4)
    s.push(5)
    s.push(9)
    print("栈顶元素为："+str(s.top()))
    print("栈大小元素为：" + str(s.size()))
    s.pop()
    print("弹栈成功")
    s.pop()