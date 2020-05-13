import math

N = int(input())
surfaceX, surfaceY = [], []
for i in range(N):
    landX, landY = [int(j) for j in input().split()]
    surfaceX.append(landX)
    surfaceY.append(landY)

while 1:
    X, Y, dX, dY, fuel, rotate, power = [int(i) for i in input().split()]

    # Finds landing altitude by Looking for 2 consecutive points w/ same Y
    groundY, i = -1, 0
    while (i < N) and (groundY == -1):
        if (surfaceX[i] <= X) and (X <= surfaceX[i + 1]):
            groundY = surfaceY[i]
        i += 1

    # Finds out if it is safe to start braking only next turn, by
    # simulating it and analyzing vertical speed at landing
    # If it is safe, waits, else starts braking
    vdY , vY = dY - 8.555, Y - 36.665 + 5 * dY;
    t = math.ceil((-40 - vdY) / 0.289);
    if (vY + t*(vdY + 0.289 * (1 + t) / 2) > groundY):
        print(0, 0)
    else:
        print(0, 4)
