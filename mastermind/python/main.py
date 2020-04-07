#!/usr/bin/python3
# -*- coding: utf-8 -*-
"""Main logic."""
from mastermind import Player


def main():
    """Main function.
    It is responsible for reading the answers, retrieving the guesses from the
    Player and writing them."""

    # instantiates the player, gets the first guess and play
    game_player = Player()
    guess = game_player.get_first_guess()

    print(guess)
    answer = input()

    while answer != '40':
        # parses the answer and gets the next guess
        answer = (int(answer[0]), int(answer[1]))
        guess = game_player.get_next_guess(answer)

        # if there is no guess left, print error and exit
        if not guess:
            print('erro')
            return

        # otherwise, play the error and get the answer
        print(guess)
        answer = input()

    print('ganhei')


if __name__ == '__main__':
    main()
