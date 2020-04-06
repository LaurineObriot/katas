<?php

class FizzBuzz
{
    public function play(int $number)
    {
        if ($number%15 === 0) {
            return 'FizzBuzz';
        }

        if ($number%5 === 0) {
            return 'Buzz';
        }

        if ($number%3 === 0) {
            return 'Fizz';
        }

        return (string) $number;
    }
}
