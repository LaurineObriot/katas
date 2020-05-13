#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv)
{
    int oldY = -1;
    int target = -1;
    int x, y, max, h;

    for (;;) {
        scanf("%d%d", &x, &y);

        /* One new ammo is available each time the height change
           The highest moutain is the new target */
        if (y != oldY) {
            oldY = y;
            max = 0;
            for (int i = 0; i < 8; i++) {
                scanf("%d", &h);
                if (h > max) {
                    target = i;
                    max = h;
                }
            }
        }
        // If height hasn't change, flushes stdin
        else
            for (int i = 0; i < 8; i++)
                scanf("%d", &h);

        printf(x == target ? "FIRE\n" : "HOLD\n");
    }
    return EXIT_SUCCESS;
}
