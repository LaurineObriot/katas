
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv)
{
    int R, L;
    scanf("%d\n%d\n", &R, &L);

    char line[10000];
    char newline[10000];
    sprintf(newline, "%d", R);

    for (int i = 1; i < L; i++) {
        /* copies the last known term of the sequence in 'line'
           the next term will be stored into 'newline'*/
        strncpy(line, newline, 10000);
        char *token;
        char *iter = newline;

        int val = -1;
        int count = 0;
        /* Parses the last known term
          updating the number of consecutive identical digits
          and printing when the digit changes*/
        token = strtok(line," ");
        while (token != NULL) {
            int current = atoi(token);
            if (current == val)
                count++;
            else {
                if (count != 0)
                    iter += sprintf(iter, "%d %d ", count, val);
                val = current;
                count = 1;
            }
            token = strtok(NULL," ");
        }
        sprintf(iter, "%d %d\n", count, val);
    }

    printf(newline);
    return EXIT_SUCCESS;
}
            
