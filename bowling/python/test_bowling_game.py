import unittest
from bowling_game import BowlingGame

class BowlingGameTestCase(unittest.TestCase):

    def roll_many(self, amount, pins):
        for i in range(0,amount):
            self.game.roll(pins)

    def test_gutter_game(self):
        self.game = BowlingGame()
        self.roll_many(20, 0)
        self.assertEqual(0, self.game.score())

    def test_all_ones(self):
        self.game = BowlingGame()
        self.roll_many(20, 1)
        self.assertEqual(20, self.game.score())

    def test_one_spare(self):
        self.game = BowlingGame()
        self.game.roll(5)
        self.game.roll(5)
        self.game.roll(3)
        self.roll_many(17, 0)
        self.assertEqual(16, self.game.score())

if __name__ == '__main__':
    unittest.main()
