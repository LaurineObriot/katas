CC=g++

main : main.o roman_numerals.o
		$(CC) -o roman main.o roman_numerals.o -std=c++11

main.o : main.cpp roman_numerals.hpp
		$(CC) -c main.cpp -std=c++11
        
roman_numerals.o : roman_numerals.cpp
		$(CC) -c roman_numerals.cpp -std=c++11

clean :
		rm roman main.o roman_numerals.o
