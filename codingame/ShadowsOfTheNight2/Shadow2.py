import sys, math

w,h = [int(i) for i in input().split()]
input() # number of turns before game over is useless
x0, y0 = [int(i) for i in input().split()]

# x0 y0 will be used to store the previous position
# and x y the current position
x, y = x0, y0

# xs*ys is the area where the bomb could be
# we'll first narrow down the area to a column by dichotomy on xaxis
# then to a single windows by dichotomy on yaxis
xs, ys = range(w), range(h)


def narrow(x0,y0,x,y,xs,ys,info):
    print("narrow : x0={}, y0={}, x={}, y={}, info={}".format(x0, y0, x, y, info), file=sys.stderr)
    # xaxis dichotomy
    if len(xs) != 1:
        if info == "UNKNOWN":
            pass
        elif info == "SAME":
            xs = [i for i in xs if abs(x0 - i) == abs(x - i)]
        elif info == "WARMER":
            xs = [i for i in xs if abs(x0 - i) > abs(x - i)]
        else:
            xs = [i for i in xs if abs(x0 - i) < abs(x - i)]
    #yaxis dichotomy
    else:
        if info == "UNKNOWN":
            pass
        elif info == "SAME":
            ys = [i for i in ys if abs(y0 - i) == abs(y - i)]
        elif info == "WARMER":
            ys = [i for i in ys if abs(y0 - i) > abs(y - i)]
        else:
            ys = [i for i in ys if abs(y0-i) < abs(y - i)]
    print(xs, file=sys.stderr)
    print(ys, file=sys.stderr)
    return xs, ys


while 1:
    info = input()
    # uses infos to narrow the area where the bomb could be
    xs, ys = narrow(x0, y0, x, y, xs, ys, info)
    # chooses the new location so that it allows to split the area in half next turn
    x0, y0 = x, y
    # dichotomy along x axis
    if len(xs) != 1:
        # the bisection between x0 and x should cut the area in 2 so:
        # (x + x0)/2 = (xs[0] + xs[-1])/2
        # little trick
        if (x0 == 0 and len(xs) != w):
            x = (3 * xs[0] + xs[-1])//2 - x0
        elif (x0 == w-1 and len(xs) != w):
            x = (xs[0] + 3 * xs[-1])//2 - x0
        else:
            x = xs[0] + xs[-1] - x0

        # to avoid fixed points
        if x == x0:
            x += 1
        x = min(max(x, 0), w - 1)

    else:
    # transition to second dichotomy
        if x != xs[0]:
            x = x0 = xs[0]
            print(x, y)
            info = input()
        # finishing
        if len(ys) == 1:
            y = ys[0]
        # dichotomy along y axis
        else:
            if (y0 == 0 and len(ys) != h):
                y = (3 * ys[0] + ys[-1])//2 - y0
            elif (y0 == h-1 and len(ys) != h):
                y = (ys[0] + 3 * ys[-1])//2 - y0
            else:
                y = ys[0] + ys[-1] - y0
            y = min(max(y, 0), h - 1)

    print(x, y)
