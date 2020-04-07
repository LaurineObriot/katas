/*
 * main.cpp
 *
 * part of
 * licensed under GNU General Public License 3 or later
 */

#include <iostream>
#include "roman_numerals.hpp"

int main(int argc, char **argv)
{
	RomanNumerals romanNumerals;
	std::cout << romanNumerals.to_roman(1000) << '\n'; // should return 'M'
	std::cout << romanNumerals.to_roman(1111) << '\n';
	std::cout << romanNumerals.to_roman(2999) << '\n';
	std::cout << romanNumerals.to_roman(1666) << '\n';
	std::cout << romanNumerals.to_roman(2008) << '\n';
	std::cout << romanNumerals.to_roman(1942) << '\n';
	std::cout << romanNumerals.to_roman(0) << '\n';
	std::cout << romanNumerals.from_roman("M") << '\n'; // should return 1000
	std::cout << romanNumerals.from_roman("MCMI") << '\n';
	std::cout << romanNumerals.from_roman("MCMXXII") << '\n';
	std::cout << romanNumerals.from_roman("MMMXXXIV") << '\n';
	std::cout << romanNumerals.from_roman("MMMXCIV") << '\n';
	std::cout << romanNumerals.from_roman("MMMXCIII") << '\n';
	std::cout << romanNumerals.from_roman("MMVIII") << '\n';
	std::cout << romanNumerals.from_roman("MDCLXVI") << '\n';
	std::cout << romanNumerals.from_roman("") << '\n';
	return 0;
}

