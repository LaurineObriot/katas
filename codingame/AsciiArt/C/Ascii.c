// More dynamic allocation, string manipulation. Note the usage of preprocessor macros to make the code easyly maintainable : in a few keystrokes it would be possible to change the number of letters in the alphabet.

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define FIRST_CHAR 'a'
#define LAST_CHAR 'z'
#define QU_MARK (LAST_CHAR+1)

// returns a substring of 'str' from index 'begin' (included) to index 'end'
char *subString(char *str, int begin, int end) {
    int len = end-begin;
    char *sub = malloc((len+1)*sizeof(char));
    strncpy(sub, str+begin, len);
    sub[len] = '\0';
    return sub;
}

/* prints the 'l'-th line of the representation of character 'c'
   in ASCII-art alphabet 'abc'. 'w' is the width of one character*/
void printChar(int l, char c, char** abc, int w) {
    int begin = (c-FIRST_CHAR)*w;
    int end = begin+w;
    printf("%s", subString(abc[l], begin, end));
}

int main(int argc, char** argv)
{
    // width and height of the ASCII-art representation of 1 character
    int width, height;
    scanf("%d\n%d\n", &width, &height);

    // text to convert, be careful, fgets store the \n
    char text[256];
    fgets(text, 256, stdin);

    // ASCII art representations of the whole alphabet + ?
    char** rows = malloc(height*sizeof(char*));
    for (int i = 0; i < height; i++) {
        rows[i] = malloc(1024*sizeof(char));
        fgets(rows[i], 1024, stdin);
    }

    // prints the result line by line, character by character
    char c;
    for (int l = 0; l < height; l++) {
        int i = 0;
        while ((c = tolower(text[i])) != '\n') {
            if (FIRST_CHAR <= c && c <= LAST_CHAR)
                printChar(l, c, rows, width);
            else
                printChar(l, QU_MARK, rows, width);
            i++;
        }
        printf("\n");
    }

    return EXIT_SUCCESS;
}
            
