class Solution:
    def minNumberInRotateArray(self, rotateArray):
        length = len(rotateArray)
        if length == 0:
            return 0
        if length == 1:
            return rotateArray[0]
        left, right = 0, length- 1
        while left<= right:
            mid = (left+ right) >> 1
            if rotateArray[mid] > rotateArray[right]:
                left = mid+ 1
            elif rotateArray[mid] < rotateArray[right]:
                right = mid
            else:
                right -= 1
            if left >= right:
                break
        return rotateArray[left]