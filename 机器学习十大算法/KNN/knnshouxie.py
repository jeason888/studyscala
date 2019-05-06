__author__ = 'wangjianxin'
#coding:utf-8
# -*- coding: gb2312 -*-
import os
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
    maxCount = 0
    for key, value in classCount.items():
        if value > maxCount:
            maxCount = value
            classes = key
    return classes
def img2vector(filename):
    returnVect=zeros((1,1024))
    fr=open(filename)
    for i in range(32):
        lineStr=fr.readline()
        for j in range(32):
            returnVect[0,32*i+j]=int(lineStr[j])
    return returnVect
def handwritingClassTest():
    hwLabels=[]
    trainingFilelist=os.listdir('E:/ml/knn/trainingDigits')
    m=len(trainingFilelist)
    trainingMat=zeros((m,1024))
    for i in range(m):
        fileNameStr=trainingFilelist[i]
        fileStr=fileNameStr.split('.')[0]
        classNumStr=int(fileStr.split('_')[0])
        hwLabels.append(classNumStr)
        trainingMat[i,:]=img2vector('E:/ml/knn/trainingDigits/%s'% fileNameStr)
    testFileList=os.listdir('E:/ml/knn/testDigits')
    errorCount=0.0
    mTest=len(testFileList)
    for i in range(mTest):
        fileNameStr = testFileList[i]
        fileStr = fileNameStr.split('.')[0]
        classNumStr = int(fileStr.split('_')[0])
        vectorUnderTest=img2vector('E:/ml/knn/testDigits/%s'% fileNameStr)
        classfierResult=classify0(vectorUnderTest,trainingMat,hwLabels,3)
        # print("the classifier came back with:%d.the real answer is:%d"%(classfierResult,classNumStr))
        if(classfierResult!=classNumStr):errorCount+=1.0
    print("\n the total number of errors is : %d"%errorCount)
    print("\n the total error rate is:%f"%(errorCount/float(mTest)))
if __name__=="__main__":
    handwritingClassTest()
