#include <stdlib.h>
#include <stdio.h>

int main()
{
    int width, height;
    scanf("%d\n%d\n", &width, &height);

    // Initializes the grid
    char grid[width][height];
    char line[31];
    for (int i = 0; i < height; i++) {
        fgets(line,31,stdin);
        for (int j = 0; j < width ; j++)
            grid[j][i] = line[j];
    }

    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (grid[j][i] == '0') {
                printf("%d %d ", j, i);

                // Search for the next node on the line
                int jj = 1;
                while ((j+jj < width) && (grid[j+jj][i] == '.'))
                    jj++;
                if (j+jj < width)
                    printf("%d %d ", j+jj, i);
                else
                    printf("%d %d ", -1, -1);

                // Search for the next node on the row
                int ii = 1;
                while ((i+ii < height) && (grid[j][i+ii] == '.'))
                    ii++;
                if (i+ii < height)
                    printf("%d %d\n", j, i + ii);
                else
                    printf("%d %d\n", -1, -1);

            }
        }
    }
}
