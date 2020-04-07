#!/usr/bin/python3
# -*- coding: utf-8 -*-
"""Mastermind game player."""

from itertools import product


class Player:
    """Mastermind game player.
    This class is responsible for being a Mastermind game solver and generating
    the guesses to be played in a game.
    """

    def __init__(self):
        self.__unused_guesses = list(
            ''.join(x) for x in product('123456', repeat=4))
        self.__possible_guesses = list(
            ''.join(x) for x in product('123456', repeat=4))
        self.__last_guess = '1122'

    @staticmethod
    def __check_answer(code, guess):
        """Calculates good and regular elements from the player guess.
        For every element in the guess, checks if it has the same value as the
        element in the same index of the code. If it does, adds 1 to the good
        elements count. Otherwise, checks if that guess element is present in
        the code. If it is, adds 1 to the regular elements count.
        Args:
            code (str): The game code.
            guess (str): The player guess in that round.
        Returns:
            int, int: A tuple containing the number of good and regular elements in
                      the player guess.
        """
        good = 0
        regular = 0

        for i in range(4):
            # if guess[i] equals code[i], increments the good elements count
            if guess[i] == code[i]:
                good += 1
            # otherwise, increments the regular elements count if guess[i] is
            # present in the code
            elif guess[i] in code:
                regular += 1

        return (good, regular)

    @classmethod
    def __get_possible_guesses(cls, guesses, guess, answer):
        """Retrieves possible codes given a game guess and its answer.
        Given a game guess and its good and regular elements answer, retrieves
        all the codes that would match this answer for that guess.
        Args:
            guesses (list): List of all guesses to be chosen from.
            guess (str): Player guess.
            answer (int, int): Number of good and regular elements when the
                               guess is played against the code.
        Returns:
            list: A list with all possible guesses.
        """
        return [
            code for code in guesses
            if cls.__check_answer(code, guess) == answer
        ]

    @classmethod
    def __minimax_guess(cls, unused_guesses, possible_guesses):
        """Returns best guess using Knuth's minimax algorithm.
        Apply minimax technique to find a next guess as follows:
        * S is the list of possible guesses
        * U is the list of unused guesses (from all the 1296, not only those in
          S)
        * A is the list of all possible answers of good and regular elements
        For each pair of guess in U and answer in A, there is a hit count for
        codes in S that would match these guess and answer.
        * the score of a guess-answer pair is the length of S minus the hit
        count for this pair
        * the score of a guess in U is the mininum score of pairs of this guess
        and all answers in A
        From the set of the guesses with maximum score, select the first one,
        choosing a guess that is present in S, if applicable.
        Args:
            possible_guesses (list): Current possible guesses.
        Returns:
            str: The best next guess calculated by Knuth's minimax.
        """
        best_guesses = []
        best_score = 0
        n_guesses = len(possible_guesses)

        for guess in unused_guesses:
            answer_count = {}
            for code in possible_guesses:
                # for each guess in U and code in S, calculates the answer and
                # stores it in the answer count
                answer = cls.__check_answer(code, guess)
                if answer not in answer_count:
                    answer_count[answer] = 1
                else:
                    answer_count[answer] += 1

            # the guess score is the maximum number of code matches it has for
            # a single answer, i.e., the minimum number of possibilities it
            # might eliminate from S
            guess_score = min([n_guesses - x for x in answer_count.values()])

            if guess_score == best_score:
                best_guesses.append(guess)
            elif guess_score > best_score:
                best_score = guess_score
                best_guesses = [guess]

        # uses as next guess an element of S, if possible. otherwise, select the
        # first element of the best guesses list
        best_guess = next((guess for guess in best_guesses
                           if guess in possible_guesses), best_guesses[0])
        return best_guess

    def get_first_guess(self):
        """Returns the first player guess.
        Returns:
            str: The first guess to be played.
        """
        self.__last_guess = '1122'
        return self.__last_guess

    def get_next_guess(self, answer):
        """Gets the next best guess.
        Args:
            answer (int, int): Number of good and regular elements from the last
                               player guess.
        Returns:
            str: The best guess to play next.
                 None if there is not guess left to play.
        """
        # remove impossible guesses given last guess and its answer
        self.__possible_guesses = self.__get_possible_guesses(
            self.__possible_guesses, self.__last_guess, answer)

        # if there is no possible guess left, there is an error
        if not self.__possible_guesses:
            return None

        # remove last guess from unused guesses
        self.__unused_guesses.remove(self.__last_guess)

        # calculates best next guess and returns it
        self.__last_guess = self.__minimax_guess(self.__unused_guesses,
                                                 self.__possible_guesses)

        return self.__last_guess
