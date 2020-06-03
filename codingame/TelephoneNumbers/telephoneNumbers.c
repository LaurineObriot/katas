#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct item {
    int digit;
    int nbChildren;
    struct item* children[10];
};

// Returns a new item which has the digit 'd'
struct item *newItem(int d)
{
    struct item *it = malloc(sizeof(*it));
    it->digit = d;
    it->nbChildren = 0;
    return it;
}

// If it exits, returns a pointer toward the child of item 'f'
// which has the digit 'digit', else returns NULL
struct item *getChild(struct item *f, int d)
{
    for (int i = 0; i < f->nbChildren ;i++)
        if (f->children[i]->digit == d)
            return f->children[i];
    return NULL;
}

// Add a child which has the digit 'digit' to item 'father'
void addChild(struct item *f, int d)
{
    struct item *child = newItem(d);
    f->children[f->nbChildren] = child;
    f->nbChildren++;
}

// Frees an item and its children recursively
void freeItem(struct item *it)
{
    for (int i = 0; i < it->nbChildren; i++)
        free(it->children[i]);
    free(it);
}

int main(int argc, char **argv)
{
    int N;
    scanf("%d\n", &N);

    int result = 0;
    struct item *root = newItem(-1);

    for (int i = 0; i < N; i++) {
        int c;
        struct item *current = root;

        /* Reads through the telephone number, moving along the corresponding
           branches of the tree and adding nodes if needed*/
        while ((c = getchar()) != '\n' && c != EOF) {
            if (getChild(current, c-'0') == NULL) {
                addChild(current, c-'0');
                result++;
            }
            current = getChild(current, c-'0');
        }
    }
    free(root);
    printf("%d\n", result);
    return EXIT_SUCCESS;
}
            
