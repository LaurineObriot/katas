// This puzzle is a simplified version of the hard puzzle "Skynet : the bridge". The aim is to drive a motorbike to make it jump over a gap at the right speed and time. Validation tests are specific cases, that can be passed by implementing the algorithm given in the problem statement. Accelerate until you have enough speed to cross the gap, keep that speed until its time to jump and brake after the gap.

#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv)
{
    int before, gap, after;
    scanf("%d%d%d\n", &before, &gap, &after);

    for (;;) {
        // x = motorbike abscissa ; dx = motorbike speed
        int dx, x;
        scanf("%d%d", &dx, &x);

        // if the bike has passed the gap, or going faster than needed to jump over it
        if (x >= before+gap || dx > gap+1)
            printf("SLOW\n");
        // if the bike isn't going fast enough to jump over the gap
        else if (dx <= gap)
            printf("SPEED\n");
        // if the bike can land after the gap by jumping
        else if (x+dx >= before+gap)
            printf("JUMP\n");
        else
            printf("WAIT\n");
    }

    return EXIT_SUCCESS;
}
