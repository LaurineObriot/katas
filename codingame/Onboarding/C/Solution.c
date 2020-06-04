#include <stdlib.h>
#include <stdio.h>

// Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

int main(int argc, char** argv)
{
    while (1) {
        char enemy1[256], enemy2[256];
        int dist1, dist2;
        scanf("%s %d %s %d", enemy1, &dist1, enemy2, &dist2);

        if (dist1 < dist2)
            printf("%s\n",enemy1);
        else
            printf("%s\n",enemy2);
    }

    return EXIT_SUCCESS;
}
