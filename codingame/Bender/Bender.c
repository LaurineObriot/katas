#include <stdlib.h>
#include <stdio.h>

struct Coords {
    int X;
    int Y;
};

// gathered the variables in a struct to simplify code
struct Infos {
    struct Coords c;
    // order in which bender tries the 4 directions, point on norder or rorder
    char* order;
    // 1 if bender is in breaker mode, 0 else
    int breaker;
    // S, E, W or N
    char direction;
    struct Coords teleporters[2];
};

char norder[4] = { 'S', 'E', 'N', 'W' };
char rorder[4] = { 'W', 'N', 'E', 'S' };

// array representing the sequence of moves taken by Bender
int nbMoves = 0;
char* moves[200];

// Returns the coordinates of the next Tile bender will move on to
struct Coords nextTile (struct Infos *bender){
    struct Coords res = { bender->c.X , bender->c.Y };
    switch (bender->direction) {
        case 'S':
            res.Y++; break;
        case 'W':
            res.X--; break;
        case 'E':
            res.X++; break;
        case 'N':
            res.Y--; break;
    }
    return res;
}

// Makes bender move on, update variables according to what is found on next tile
void go (struct Infos *bender, char** map){
    switch (bender->direction) {
        case 'N':
            moves[nbMoves] = "NORTH\n";
            bender->c.Y--;
            break;
        case 'S':
            moves[nbMoves] = "SOUTH\n";
            bender->c.Y++;
            break;
        case 'E':
            moves[nbMoves] = "EAST\n";
            bender->c.X++;
            break;
        case 'W':
            moves[nbMoves] = "WEST\n";
            bender->c.X--;
            break;
    }
    nbMoves++;
    switch (map[bender->c.Y][bender->c.X]) {
        case 'X':
            map[bender->c.Y][bender->c.X] = ' ';
            break;
        case 'B':
            bender->breaker = !bender->breaker;
            break;
        case 'I':
            bender->order = (bender->order == norder)?rorder:norder;
            break;
        case 'S':
        case 'N':
        case 'E':
        case 'W':
            bender->direction = map[bender->c.Y][bender->c.X];
            break;
        case 'T':
            teleport(bender);
            break;
    }
}

// computes the direction Bender will take according to order, breaker mode, etc
void changeDirection (struct Infos *bender, char** map){
    int i = 0;
    struct Coords t = nextTile(bender);
    while (map[t.Y][t.X] == '#' || (!bender->breaker && map[t.Y][t.X] == 'X')) {
            bender->direction = bender->order[i++];
            t = nextTile(bender);
    }
}

// teleports Bender from the teleporter it is on to the other
void teleport (struct Infos *bender){
    if (bender->c.X == bender->teleporters[0].X &&
        bender->c.Y == bender->teleporters[0].Y)
    {
        bender->c.X = bender->teleporters[1].X;
        bender->c.Y = bender->teleporters[1].Y;
    }
    else {
        bender->c.X = bender->teleporters[0].X;
        bender->c.Y = bender->teleporters[0].Y;
    }
}

int main(int argc, char **argv)
{
    struct Infos bender;

    // Read input
    int L, C;
    scanf("%d %d\n", &L, &C);
    char** map = malloc(L*sizeof(char*));
    int telep = 0;
    for (int i = 0; i < L; i++) {
        map[i] = malloc((C+1)*sizeof(char));
        gets(map[i]);
        for (int j = 0; j < C; j++) {
            if (map[i][j] == '@') {
                bender.c.X = j;
                bender.c.Y = i;
            }
            if (map[i][j] == 'T') {
                bender.teleporters[telep].X = j;
                bender.teleporters[telep++].Y = i;
            }
        }
    }

    bender.order = norder;
    bender.breaker = 0;
    bender.direction = 'S';

    while (map[bender.c.Y][bender.c.X] != '$' && nbMoves < 200) {
        struct Coords t = nextTile(&bender);
        switch(map[t.Y][t.X]) {
            case ' ':
            case '$':
            case 'E':
            case 'W':
            case 'S':
            case 'N':
            case 'B':
            case 'I':
            case 'T':
                go(&bender, map);
                break;
            case '#':
            case 'X':
                changeDirection(&bender, map);
                go(&bender, map);
                break;
        }
    }

    if (nbMoves == 200)
        printf("LOOP\n");
    else
        for (int i = 0; i < nbMoves ; i++)
            printf(moves[i]);

    // frees memory
    for (int i = 0; i < L; i++)
        free(map[i]);
    free(map);

    return EXIT_SUCCESS;
}
