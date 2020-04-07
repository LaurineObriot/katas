/*
 * roman_numerals.hpp
 *
 * part of
 * licensed under GNU General Public License 3 or later
 */

#ifndef _ROMAN_NUMERALS_HPP
#define _ROMAN_NUMERALS_HPP

#include <string>
#include <map>

class RomanNumerals {
	private:
		static const std::map<char, int> FROM_ROMAN_NUMS;
		static const std::map<int, std::string> TO_ROMAN_NUMS;

	public:
		int from_roman(std::string romanNumber);
		std::string to_roman(int number);
};

#endif