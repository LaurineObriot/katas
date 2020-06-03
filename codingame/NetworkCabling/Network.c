#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>

/* Comparison function between two long long integers
   return -1 if a<b, 1 if a>b, 0 if a=b */
int cmp (const void * a, const void * b){
    long long aa = *(long long*)a;
    long long bb = *(long long*)b;
    return aa<bb?-1:aa>bb?1:0;
}

int main()
{
    int N;
    scanf("%d", &N);

    long long xMin = LLONG_MAX;
    long long xMax = 0LL;
    long long x;
    long long Ys[N];

    // reading coordinates, storing Ys, computing xMin and xMax
    for (int i = 0; i < N; i++) {
        long x;
        scanf("%Ld %Ld", &x, Ys+i);
        if (x < xMin) xMin = x;
        if (x > xMax) xMax = x;
    }
    // sorting Ys in ascending order
    qsort(Ys, N, sizeof(long long), cmp);

    /* The main horizontal cable length is xMax-xMin
       To minimize the total length of vertical cables, its
       y-coordinate of the main cable must be a median of Ys, Ys[N/2]*/
    long long res = xMax-xMin;
    for (int i = 0 ; i < N ; i++)
        res += abs(Ys[N/2]-Ys[i]);

    printf("%Ld\n", res);
}
