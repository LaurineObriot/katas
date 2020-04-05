#include "FizzBuzzListGenerator.h"
#include <ctype.h>
#include <string>
#include <iostream>

#include "UnknownNumberException.h"
#include "OutOfBoundsException.h"

FizzBuzzListGenerator::FizzBuzzListGenerator()
{
}

FizzBuzzListGenerator::~FizzBuzzListGenerator()
{
}

string FizzBuzzListGenerator::generateListWithThisManyInts(const int numberOfInts) const
{
	string myList = "";
	FizzBuzzNumber *n = NULL;

	for (int i = 0; i < numberOfInts; i++)
	{
		n = new FizzBuzzNumber(i + 1);
		myList += n->getFizzBuzz() + "\n";
		delete n;
	}

	return myList;
}
