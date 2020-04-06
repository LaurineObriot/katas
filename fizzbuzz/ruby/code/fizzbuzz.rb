def is_divisible_by_three?(number)
	is_divisible_by?(number, 3)
end

def is_divisible_by_five?(number)
	is_divisible_by?(number, 5)
end

def is_divisible_by_fifteen?(number)
	is_divisible_by?(number, 15)
end

def is_divisible_by?(number, divisor)
	number % divisor == 0
end

def fizzbuzz(number)
	return 'FizzBuzz' if is_divisible_by_fifteen?(number)
	return 'Fizz' if is_divisible_by_three?(number)
	return 'Buzz' if is_divisible_by_five?(number)
	number
end
