def sum(string):
    sum = 0
    for value in string:
        sum += int(value)
    return str(sum)

def digitalroot(string):
    while(len(string)>1):
        string = sum(string)
    return string

while(True):
    inputString = input()
    if (inputString == "0"):
        break
    print(digitalroot(inputString))