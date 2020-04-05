#pragma once

#include <string>

using namespace std;

class FizzBuzzNumber
{
public:
	FizzBuzzNumber(int number);
	~FizzBuzzNumber();

	bool isFizz() const;
	bool isBuzz() const;

	string getFizzBuzz();

private:
	int theNumber;
	string fizzBuzzString;
};
