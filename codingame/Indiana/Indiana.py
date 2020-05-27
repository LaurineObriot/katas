, H = [int(i) for i in input().split()]
rooms = []
for i in range(H):
    rooms.append([int(i) for i in input().split()])
exit = int(input())

while 1:
    XI, YI, POS = input().split()
    XI, YI = int(XI), int(YI)

    if rooms[YI][XI] in (1, 3, 7, 8, 9, 12, 13):
        YI+=1
    elif rooms[YI][XI] in (2, 6):
        XI+= 1 if POS == 'LEFT' else -1
    elif rooms[YI][XI] == 4:
        if POS == 'TOP':
            XI-=1
        else:
            YI+=1
    elif rooms[YI][XI] == 5:
        if POS == 'TOP':
            XI+=1
        else:
            YI+=1
    elif rooms[YI][XI] == 10:
        XI-=1
    elif rooms[YI][XI] == 11:
        XI+=1

    print("{} {}".format(XI, YI))
