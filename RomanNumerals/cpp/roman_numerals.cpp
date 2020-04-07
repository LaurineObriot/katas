/*
 * roman_numerals.cpp
 *
 * part of
 * licensed under GNU General Public License 3 or later
 */

#include <string>
#include <map>
#include "roman_numerals.hpp"

const std::map<char, int> RomanNumerals::FROM_ROMAN_NUMS = {
	{'I',   1},
	{'V',   5},
	{'X',   10},
	{'L',   50},
	{'C',   100},
	{'D',   500},
	{'M',   1000}
};

const std::map<int, std::string> RomanNumerals::TO_ROMAN_NUMS = {
	{1,     "I"},
	{5,     "V"},
	{10,    "X"},
	{50,    "L"},
	{100,   "C"},
	{500,   "D"},
	{1000,  "M"}
};

int RomanNumerals::from_roman(std::string romanNumber)
{
	int result = 0;

	if (romanNumber == "") {
		return result;
	}

	int i = 0;
	int currentNumber = 0;
	int nextNumber = 0;

	while (i < romanNumber.size()-1) {

		currentNumber = RomanNumerals::FROM_ROMAN_NUMS.at(romanNumber[i]);
		nextNumber = RomanNumerals::FROM_ROMAN_NUMS.at(romanNumber[i+1]);

		if (currentNumber < nextNumber){
			result += (nextNumber - currentNumber);
			i += 2;
		}
		else {
			result += currentNumber;
			i++;
		}

	}

	if (i < romanNumber.size()) {
		result += RomanNumerals::FROM_ROMAN_NUMS.at(romanNumber[i]);
	}

	return result;
}


std::string RomanNumerals::to_roman(int number)
{
	std::string result = "";
	int currentMagnitude = 1000;

	int currentNumber = number / currentMagnitude;

	// thousands
	for (int t = 1; t <= currentNumber; t++) {
		result = result + RomanNumerals::TO_ROMAN_NUMS.at(1000);
	}

	number = number % currentMagnitude;
	currentMagnitude = currentMagnitude / 10;

	//rest of numbers
	while (currentMagnitude > 0) {

		currentNumber = number / currentMagnitude;

		switch (currentNumber) {

		case 5:
		case 6:
		case 7:
		case 8:
			result = result + RomanNumerals::TO_ROMAN_NUMS.at(5 * currentMagnitude);
			// no break here
		case 1:
		case 2:
		case 3:
			for (int i = 1; i <= (currentNumber % 5); i++) {
				result = result + RomanNumerals::TO_ROMAN_NUMS.at(currentMagnitude);
			}
			break;

		case 4:
			result = result + RomanNumerals::TO_ROMAN_NUMS.at(currentMagnitude)
					 + RomanNumerals::TO_ROMAN_NUMS.at(5 * currentMagnitude);
			break;

		case 9:
			result = result + RomanNumerals::TO_ROMAN_NUMS.at(currentMagnitude)
					 + RomanNumerals::TO_ROMAN_NUMS.at(10 * currentMagnitude);
			break;

		default:
			// catches 0
			// nothing to do
			break;
		}

		number = number % currentMagnitude;
		currentMagnitude = currentMagnitude / 10;
	}
	return result;
}








