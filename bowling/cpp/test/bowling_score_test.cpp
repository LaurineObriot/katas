#include "gtest/gtest.h"
#include "../src/game.h"

class GameTest: public testing::Test {
protected:
    Game game;

    void rollMany(int rolls, int pins) {
        for(int i = 0;i < rolls; i++){
            game.roll(pins);
        }
    }

    void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    void rollStrike(){
        game.roll(10);
    }

};

TEST_F(GameTest, GutterGame) {
    rollMany(20,0);
    ASSERT_EQ(game.score(), 0);
}

TEST_F(GameTest, AllOnes) {
    rollMany(20, 1);
    ASSERT_EQ(game.score(), 20);
}

TEST_F(GameTest, OneSpare){
    rollSpare();
    game.roll(6);
    rollMany(17, 0);
    ASSERT_EQ(game.score(), 22);
}

TEST_F(GameTest, TwoSpares){
    rollSpare();
    game.roll(6);
    game.roll(0);
    rollSpare();
    game.roll(8);
    rollMany(13, 0);
    ASSERT_EQ(game.score(), 48);
}

TEST_F(GameTest, SpareOnLastFrame) {
    rollMany(18, 0);
    rollSpare();
    game.roll(3);
    ASSERT_EQ(game.score(), 13);
}

TEST_F(GameTest, OneStrike) {
    rollStrike();
    game.roll(2);
    game.roll(7);
    rollMany(16, 0);
    ASSERT_EQ(game.score(), 28);
}

TEST_F(GameTest, PerfectGame) {
    rollMany(12, 10);
    ASSERT_EQ(game.score(), 300);
}
