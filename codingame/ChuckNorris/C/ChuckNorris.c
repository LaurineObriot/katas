// I did not find any standard function useful to convert a character to its binary ascii code, so I wrote my own function to do it. This code is a good example of how to read through strings using pointers arithmetic. It also shows how to return a string using dynamic allocation (malloc) and a pointer

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Converts character 'c' to its binary code on 7 bits
char *toBinCode (char c) {
    char *res = malloc(8*sizeof(char));
    for (int i = 6; i >= 0; i--) {
        res[i] = '0'+(c%2);
        c/=2;
    }
    res[7] = '\0';
    return res;
}

int main(int argc, char** argv)
{
    // note that fgets keeps the \n at the end of the msg
    char msg[100];
    fgets(&msg,100,stdin);
    char binMsg[700];

    /* Iterating over msg and binMsg using pointers iter, binIter
       converting each char in their binary code
       which is appended at the end of binMsg */
    char* iter = msg;
    char* binIter = binMsg;
    char *code;
    while (*iter != '\n') {
        code = toBinCode(*iter);
        strncpy(binIter, code, 7);
        free(code);
        iter++;
        binIter+=7;
    }
    *binIter = '\0';

    /* prev contains the last character of binMsg read : '0' or '1'
       count countains the number of consecutive identical characters */
    char prev = '2';
    int count = 0;
    /* reads binMsg character by character, updating prev, count
       and printing the solution gradually*/
    for (binIter = binMsg; *binIter != '\0'; binIter++) {
        if (*binIter != prev) {
            for (int j = 0; j < count; j++)
                printf("0");
            if (count != 0)
                printf(" ");
            printf((*binIter == '0') ? "00 " : "0 ");
            count = 1;
        } else
            count++;
        prev = *binIter;
    }
    for (int j = 0 ; j < count ; j++)
        printf("0");

    return EXIT_SUCCESS;
}
