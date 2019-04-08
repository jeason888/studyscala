quick_sort = lambda array: array if len(array) <=1 else quick_sort([item for item in array[1:] if item <= array[0]]) + [array[0]] + quick_sort([item for item in array[1:] if item > array[0]])
array=[4,3,2,1,5,3,2]
print(quick_sort(array))