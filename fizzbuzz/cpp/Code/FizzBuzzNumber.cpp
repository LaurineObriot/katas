#include "FizzBuzzNumber.h"

FizzBuzzNumber::FizzBuzzNumber(int number) :
	theNumber(number),
	fizzBuzzString("")
{
	if (this->isFizz())
		fizzBuzzString += "Fizz";

	if (this->isBuzz())
		fizzBuzzString += "Buzz";

	if (fizzBuzzString.length() == 0)
		fizzBuzzString = to_string(this->theNumber);
}

FizzBuzzNumber::~FizzBuzzNumber()
{
}

bool FizzBuzzNumber::isFizz() const
{
	if (theNumber % 3 == 0)
		return true;

	return false;
}

bool FizzBuzzNumber::isBuzz() const
{
	if (theNumber % 5 == 0)
		return true;

	return false;
}

string FizzBuzzNumber::getFizzBuzz()
{
	return fizzBuzzString;
}
