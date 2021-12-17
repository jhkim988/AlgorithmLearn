file = open("./input.txt", 'r');
a = int(file.readline());
b = int(file.readline());

file = open("./outputPython.txt", 'w');
file.write((str(a * b)));