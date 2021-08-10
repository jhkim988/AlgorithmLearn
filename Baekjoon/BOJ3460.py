T = int(input())
k = 0

def toBinary(num):
    result = []
    k = 0
    while(num >= 2**k):
        result.append(num%2)
        num = int(num/2)
    return result

def printOnePosition(li):
    k = 0
    for value in li:
        if(value == 1):
            print(k, end=" ")
        k += 1

while(k < T):
    inputNumber = toBinary(int(input()))
    printOnePosition(inputNumber)
    k += 1