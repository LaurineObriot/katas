from itertools import product
from behave import given, when, then
from mastermind import Player

@given('a list of possible guesses and a list of unused guesses')
def step_impl(context):
    context.possible_guesses = [''.join(x) for x in product('123456', repeat=4)]
    context.unused_guesses = [''.join(x) for x in product('123456', repeat=4)]

@when('I solicit the next best guess')
def step_impl(context):
    context.next_guess = Player._Player__minimax_guess(
        context.unused_guesses, context.possible_guesses)

@then('I get the expected guess')
def step_impl(context):
    assert context.next_guess == '1123'
