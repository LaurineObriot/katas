
#include <stdio.h>

int main(int argc, char *argv[]) {
  char max = argv[1][0];
  int size = max - 'A';
  int col,row;
  for(row = 0; row<size*2+1; row++) {
	  for(col = 0; col<size*2+1; col++) {
		  int letter = row < size ? row : size*2 - row;
		  putchar(col-size == letter || size-col == letter ? 'A'+letter : ' ');
	  }
	  putchar('\n');
  }
}
