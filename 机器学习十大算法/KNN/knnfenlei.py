__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
from numpy import *
import operator
import matplotlib
import matplotlib.pyplot as plt
def classify0(input, dataSet, label, k):
    dataSize = dataSet.shape[0]
    ####计算欧式距离
    diff = tile(input, (dataSize, 1)) - dataSet
    sqdiff = diff ** 2
    squareDist = sum(sqdiff, axis=1)  ###行向量分别相加，从而得到新的一个行向量
    dist = squareDist ** 0.5
    ##对距离进行排序
    sortedDistIndex = argsort(dist)  ##argsort()根据元素的值从大到小对元素进行排序，返回下标
    classCount = {}
    for i in range(k):
        voteLabel = label[sortedDistIndex[i]]
        ###对选取的K个样本所属的类别个数进行统计
        classCount[voteLabel] = classCount.get(voteLabel, 0) + 1
    ###选取出现的类别次数最多的类别
    print(classCount)
    maxCount = 0
    for key, value in classCount.items():
        if value > maxCount:
            maxCount = value
            classes = key
    return classes

def file2matrix(filename):
    fr=open(filename)
    arrayOLines=fr.readlines()
    numberOfLines=len(arrayOLines)
    #zeros函数是生成numberOfLines X 3的矩阵，是array型的
    returnMat=zeros((numberOfLines,3))
    classLabelVector=[]
    index=0
    for line in arrayOLines:
        line=line.strip()
        listFromLine=line.split("\t")
        returnMat[index,:]=listFromLine[0:3]
        classLabelVector.append(int(listFromLine[-1]))
        index+=1
    return returnMat,classLabelVector
def autoNorm(dataSet):
    minVals=dataSet.min(0)
    maxVals=dataSet.max(0)
    ranges=maxVals-minVals
    normDataSet=zeros(shape(dataSet))
    m=dataSet.shape[0]
    normDataSet=dataSet-tile(minVals,(m,1))
    normDataSet=normDataSet/tile(ranges,(m,1))
    return normDataSet,ranges,minVals
def datingClassTest():
    hoRatio = 0.50
    datingDataMat,datingLabels = file2matrix('E:/ml/knn/k-近邻算法/datingTestSet2.txt')       #load data setfrom file
    normMat, ranges, minVals = autoNorm(datingDataMat)
    m = normMat.shape[0]
    print(normMat[0, :])
    numTestVecs = int(m*hoRatio)
    errorCount = 0.0
    for i in range(numTestVecs):
        classifierResult = classify0(normMat[i,:],normMat[numTestVecs:m,:],datingLabels[numTestVecs:m],6)
        print("the classifier came back with: %d, the real answer is: %d" % (classifierResult, datingLabels[i]))
        if (classifierResult != datingLabels[i]): errorCount += 1.0
    print("the total error rate is: %f" % (errorCount/float(numTestVecs)))

def classfifyPerson():
    resultList=['not at all','in small doses','in large doses']
    precentTats=float(input("precentage of time spent palying video games?"))
    ffMiles=float(input("frequent flier miles earned per year?"))
    ice_cream=float(input("liters of ice cream consumed per year?"))
    datingDataMat,datingLabels=file2matrix("E:/ml/knn/k-近邻算法/datingTestSet2.txt")
    normMat, ranges, minVals = autoNorm(datingDataMat)
    inArr=array([ffMiles,precentTats,ice_cream])
    classifierResult=classify0((inArr-minVals)/ranges,normMat,datingLabels,6)
    print("you will probably like this person:",resultList[classifierResult-1])
if __name__=="__main__":
    classfifyPerson()