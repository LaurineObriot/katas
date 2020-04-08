example: minunit.h minunit_example.c
	gcc minunit_example.c -lm -o bin/example

test: minunit.h test.c
	gcc test.c -lm -o bin/test
	bin/test

diamond: diamond.c
	gcc diamond.c -o bin/diamond
	bin/diamond "Z"
