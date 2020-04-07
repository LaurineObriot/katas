from ast import literal_eval
from behave import given, when, then
from mastermind import Player

@given('we have a guess and a code, both without repeated numbers')
def step_impl(context):
    context.guess = '5321'
    context.code = '1234'

@when('we test it against the answer')
def step_impl(context):
    context.element_count = Player._Player__check_answer(
        context.code, context.guess)

@then('we have the correct element count for both lists without repeated numbers')
def step_impl(context):
    assert context.element_count == (0, 3)

@given('we have a guess with repeated numbers and a game code without repeated numbers')
def step_impl(context):
    context.guess = '1321'
    context.code = '1234'

@then('we have the correct element count for a guess with repeated numbers')
def step_impl(context):
    assert context.element_count == (1, 3)

@given('we have a guess and a code, both with repeated numbers')
def step_impl(context):
    context.guess = '1121'
    context.code = '1241'

@then('we have the correct element count for both list with repeated numbers')
def step_impl(context):
    assert context.element_count == (2, 2)

@given('the testing "{guess}" and "{code}" given by the professor')
def step_impl(context, guess, code):
    context.guess = guess
    context.code = code

@then('we have the correct {element_count}')
def step_impl(context, element_count):
    assert context.element_count == literal_eval(element_count)
