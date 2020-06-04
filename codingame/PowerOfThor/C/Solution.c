#include <stdlib.h>
#include <stdio.h>

// For this puzzle, we want Thor to reach his light of power. All we have to do is compare Thor's coordinates with the position of its light of power and output the good direction.

int main(int argc, char** argv)
{
    // Coordinates of the light and Thor
    int lX, lY, tX, tY;
    scanf("%d%d%d%d", &lX, &lY, &tX, &tY);

    for (;;) {
        if (tY < lY) {
            printf("S");
            tY++;
        }
        else if (tY > lY) {
            printf("N");
            tY--;
        }

        if (tX < lX) {
            printf("E");
            tX++;
        }
        else if (tX > lX) {
            printf("W");
            tX--;
        }
        printf("\n");
    }
    return EXIT_SUCCESS;
}
