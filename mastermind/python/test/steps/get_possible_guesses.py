from itertools import product
from behave import given, when, then
from mastermind import Player

@given('I have an answer for a player guess')
def step_impl(context):
    code = '4321'
    context.guess = '1234'
    context.answer = Player._Player__check_answer(code, context.guess)

@when('I get the list of possible codes for this guess and the answer')
def step_impl(context):
    guesses = [''.join(x) for x in product('123456', repeat=4)]
    context.new_guesses = Player._Player__get_possible_guesses(
        guesses, context.guess, context.answer)

@then('I have the expected list of possible codes')
def step_impl(context):
    expected_guesses = ['2143', '2341', '2413', '3142', '3412', '3421', '4123',
                        '4312', '4321']
    assert context.new_guesses == expected_guesses
