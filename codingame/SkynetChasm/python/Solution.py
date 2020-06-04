# This puzzle is a simplified version of the hard puzzle "Skynet : the bridge". The aim is to drive a motorbike to make it jump over a gap at the right speed and time. Validation tests are specific cases, that can be passed by implementing the algorithm given in the problem statement. Accelerate until you have enough speed to cross the gap, keep that speed until its time to jump and brake after the gap.

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
