#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv)
{
    int N;
    scanf("%d\n", &N);

    int surfaceX[N];
    int surfaceY[N];
    for (int i = 0; i < N; i++)
        scanf("%d %d\n", &surfaceX[i], &surfaceY[i]);

    while (1) {
        int X, Y, dX, dY, fuel, rotate, power;
        scanf("%d %d %d %d %d %d %d", &X, &Y, &dX, &dY, &fuel, &rotate, &power);

        // Finds landing altitude by Looking for 2 consecutive points w/ same Y
        int groundY = -1;
        for (int i = 0; (i < N && groundY == -1); i++)
            if (surfaceX[i] <= X && X <= surfaceX[i + 1])
                groundY = surfaceY[i];

        /* Finds out if it is safe to start braking only next turn, by
           simulating it and analyzing vertical speed at landing
           If it is safe, waits, else starts braking*/
        double vdY = (double)dY;
        double vY = (double)Y;
        for (double i = 0.0; i <= 4; i++) {
            vdY += i - 3.711;
            vY += vdY;
        }
        while (vY > groundY && vdY < 0) {
            vdY += 0.289; // 4-3.711;
            vY += vdY;
        }
        printf((vdY >= 0 || vdY > -40.0) ? "0 0\n" : "0 4\n");
    }
    return EXIT_SUCCESS;
}
