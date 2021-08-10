input1 = input()
input2 = input()

arr1 = input1.split(" ")
arr2 = input2.split(" ")

numPerson = int(arr1[0])*int(arr1[1])
for value in arr2:
    print(int(value) - numPerson, end=" ")