<?php

namespace tests;

class FizzBuzz extends \atoum
{
    public function test_that_it_return_Fizz_when_number_is_a_mulitple_of_three()
    {
        $this->string($this->newTestedInstance->play(3))->isIdenticalTo('Fizz');
        $this->string($this->newTestedInstance->play(6))->isIdenticalTo('Fizz');
    }

    public function test_that_it_return_Buzz_when_number_is_a_mulitple_of_five()
    {
        $this->string($this->newTestedInstance->play(5))->isIdenticalTo('Buzz');
        $this->string($this->newTestedInstance->play(10))->isIdenticalTo('Buzz');
    }

    public function test_that_it_return_FizzBuzz_when_number_is_a_multiple_of_three_and_five()
    {
        $this->string($this->newTestedInstance->play(15))->isIdenticalTo('FizzBuzz');
        $this->string($this->newTestedInstance->play(30))->isIdenticalTo('FizzBuzz');
    }

    public function test_that_it_return_number_in_other_case()
    {
        $this->string($this->newTestedInstance->play(1))->isIdenticalTo('1');
        $this->string($this->newTestedInstance->play(2))->isIdenticalTo('2');
    }
}
