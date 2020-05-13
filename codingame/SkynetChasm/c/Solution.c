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
