import math

class BowlingGame:

    def __init__(self):
        self.current_score = 0
        self.rolls = []
        self.current_roll = 0

    def roll(self, pins):
        self.rolls.append(pins)
        self.current_roll += 1

    def score(self):
        self.current_score = 0
        
        for i in self.rolls:
            self.current_score += i

        return self.current_score
