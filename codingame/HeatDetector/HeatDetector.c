#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv) {
    int W, H, turns, x, y;
    scanf("%d %d\n%d\n%d %d\n", &W, &H, &turns, &x, &y);

    // these 4 variables circumscribe a zone where the bomb is
    int xInf = 0;
    int xSup =  W-1;
    int yInf = 0;
    int ySup = H-1;

    /* each turn, we go to the center of this zone, and the direction provided
    allows us to narrow down the zone .
    It is like a 2 dimensional binary search.*/

    char BD[2] = { ' ', ' ' };
    while (1) {
        scanf("%s", BD);

        if (BD[0] == 'U')
            ySup = y-1;
        else if (BD[0] == 'D')
            yInf = y+1;
        else
            ySup = yInf = y;

        if (BD[0] == 'R' || BD[1] == 'R')
            xInf = x+1;
        else if((BD[0] == 'L') || (BD[1] == 'L'))
            xSup = x-1;
        else
            xSup = xInf = x;

        x = (xInf + xSup)/2;
        y = (yInf + ySup)/2;
        printf("%d %d\n", x, y);
    }

    return EXIT_SUCCESS;
}
