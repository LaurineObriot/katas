#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv)
{
    // number of floors, exit position on last floor, a floor nb, a position
    int N, exit, f, pos;
    scanf("%d%*d%*d%*d%d%*d%*d%*d", &N, &exit);

    // elevators positions
    int elevators[N];
    for (int i = 0; i < N-1; i++) {
        scanf("%d", &f);
        scanf("%d", &elevators[f]);
    }
    elevators[N - 1] = exit;

    char direction[5];
    while (1) {
        scanf("%d%d%s", &f, &pos, direction);
        // -1 if direction is LEFT, 1 if it is right
        int d = strcmp(direction, "M");

        if (f == -1)
            printf("WAIT\n");
        else if ((d < 0 && pos < elevators[f]) ||
                 (d > 0 && pos > elevators[f]) )
            printf("BLOCK\n");
        else
            printf("WAIT\n");
    }
    return EXIT_SUCCESS;
}
