before, gap, after = int(input()), int(input()), int(input())

while 1:
    # x = motorbike abscissa ; dx = motorbike speed
    dx, x = int(input()), int(input())

    # if the bike is after the gap, or going faster than needed : slow
    if (x >= before + gap) or (dx > gap+1):
        print("SLOW")
    # if the bike isn't going fast enough to jump over the gap
    elif (dx <= gap):
        print("SPEED")
    #  if the bike can land after the gap by jumping
    elif (x+dx >= before+gap):
        print("JUMP")
    else:
        print("WAIT")
