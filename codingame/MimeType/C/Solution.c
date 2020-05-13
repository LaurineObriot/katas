#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Represents an association between an extension, and its mime type
struct pair { char *ext; char *mime; };

// A dictionnary can be implented very simply as a pair array
struct dict {
    int size;
    struct pair *elmts;
};

/* Returns the MIME type of label 'ext' in dictionnary 'dict'
   or "UNKNOWN" if there is no matching extension */
char *assoc (struct dict *d, char *ext) {
    for (int i = 0; i < d->size; i++)
        if (strcmp(d->elmts[i].ext, ext) == 0)
            return d->elmts[i].mime;
    return "UNKNOWN";
}

// Replaces all uppercase chars of 'str' by the corresponding lowercase chars
void toLower (char *str) {
    for (; *str != '\0'; str++)
        *str = tolower(*str);
}

// Returns a pointer on the first char of the extension of 'str' if it has one
// or NULL else
char *getExt (char *str) {
    for (char *ext = str+strlen(str); ext != str; ext--)
        if (*(ext-1) == '.')
            return ext;
    return NULL;
}

int main(int argc, char** argv)
{
    int dictSize, nbFiles;
    scanf("%d\n%d\n", &dictSize, &nbFiles);

    // Initializes the dictionary extension-MIME type
    struct dict d;
    struct pair *p;
    d.size = dictSize;
    d.elmts = malloc(d.size*sizeof(struct pair));
    for (int i = 0; i < dictSize; i++) {
        p = d.elmts+i;
        p->ext = malloc(11*sizeof(char));
        p->mime = malloc(51*sizeof(char));
        // here using scanf is quite safe because maximal lengths are known
        scanf("%s %s\n", p->ext, p->mime);
        toLower(p->ext);
    }

    // processes file 1 by 1, by isolating ext and matching it with a MIME type
    char file[257];
    char *ext;
    for (int i = 0; i < nbFiles; i++) {
        gets(file);
        toLower(file);
        ext = getExt(file);
        printf("%s\n", assoc(&d, ext?ext:""));
    }

    for (int i = 0; i < dictSize; i++) {
        p = d.elmts+i;
        free(p->ext);
        free(p->mime);
    }
    free(d.elmts);

    return EXIT_SUCCESS;
}
          
