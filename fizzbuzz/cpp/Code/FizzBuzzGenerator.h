#pragma once

#include "FizzBuzzNumber.h"

using namespace std;

class FizzBuzzListGenerator {

public:
	FizzBuzzListGenerator();
	~FizzBuzzListGenerator();

	string generateListWithThisManyInts(const int numberOfInts) const;
};
