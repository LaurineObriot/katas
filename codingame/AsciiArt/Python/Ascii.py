# some constants, ord gives the ascii code of a character (chr does the opposite)
FIRST_CHAR = ord('a')
LAST_CHAR = ord('z')
QU_MARK = LAST_CHAR+1

def printChar(l, c, rep, w):
    ''' prints the 'l'-th line of the ASCII-art representation of a character 'c'
        rep is the ASCII-art representation of the whole alphabet + ?
        and w width of the ASCII-art representation of 1 character'''
    begin = (ord(c)-FIRST_CHAR)*w
    end = begin+w
    print(rep[l][begin:end], end="")

# width and height of the ASCII-art representation of 1 character
# text to convert, ASCII art representations of the whole alphabet + ?
width, height, text, rows = int(input()), int(input()), input().lower(), []
for i in range(height):
    rows.append(input())

# prints the result line by line, character by character
for l in range(height):
    for c in text:
        if (FIRST_CHAR <= ord(c)) and (ord(c) <= LAST_CHAR):
            printChar(l, c, rows, width)
        else:
            printChar(l, chr(QU_MARK), rows, width)
    print()
