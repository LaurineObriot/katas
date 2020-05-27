#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>

int main(int argc, char **argv)
{
    // number of stock values
    int N;
    scanf("%d\n", &N);

    /* as each stock value is under 2^31 which is roughly 4*10^9,
       15 chars per value is largely enough*/
    char line[15*N];
    fgets(line,15*N,stdin);

    /* currentMax and currentMin will store the local minimum and maximum
       stock values, maxLoss will store the maximum loss found yet */
    int stockValue, maxLoss, currentMax, currentMin;
    maxLoss = currentMax = 0;
    currentMin = INT_MAX;

    // Uses strtok to read all the stock values
    char *tok;
    tok = strtok(line," ");
    for (int i = 0; i < N; i++) {
        stockValue = atoi(tok);

        if (stockValue > currentMax) {
            if (currentMax-currentMin > maxLoss)
                maxLoss = currentMax-currentMin;
            currentMax = currentMin = stockValue;
        }
        else if (stockValue < currentMin)
            currentMin = stockValue;

        tok = strtok(NULL, " ");
    }

    if (currentMax-currentMin > maxLoss)
        maxLoss = currentMax-currentMin;

    printf("%d\n", -maxLoss);
    return EXIT_SUCCESS;
}
