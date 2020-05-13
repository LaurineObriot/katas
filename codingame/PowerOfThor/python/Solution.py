# lX, lY: coordinates of the light of power
# tX, tY: Thor's coordinates
lX, lY, tX, tY = [int(i) for i in input().split()]

while (True):
    ch1 = ch2 = ""

    if tY < lY:
        ch1 = "S"
        tY+=1
    elif tY > lY:
        ch1 = "N"
        tY-=1

    if tX < lX:
        ch2 = "E"
        tX+=1
    elif tX > lX:
        ch2 = "W"
        tX-=1

    print(ch1, ch2, sep="")
