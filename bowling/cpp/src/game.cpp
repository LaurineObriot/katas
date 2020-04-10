#include "game.h"

void Game::roll(int pins) {
    rolls.push_back(pins);
}

int Game::score() {
    int score = 0;
    int frameIndex = 0;
    for (int frame = 0; frame < 10; frame++){
        if (isSpareFrame(frameIndex)) {
            score += 10 + spareBonus(frameIndex);
            frameIndex+=2;
        } else if (isStrikeFrame(frameIndex)) {
            score += 10 + strikeBonus(frameIndex);
            frameIndex++;
        } else {
            score += sumOfKnockedPinsInFrame(frameIndex);
            frameIndex+=2;
        }
    }
    return score;
}

int Game::spareBonus(int frameIndex) const { return rolls[frameIndex + 2]; }

int Game::strikeBonus(int frameIndex) const { return rolls[frameIndex+1] + rolls[frameIndex + 2]; }

int Game::sumOfKnockedPinsInFrame(int frameIndex) {
    return rolls[frameIndex] + rolls[frameIndex+1];
}

bool Game::isStrikeFrame(int frameIndex) const { return rolls[frameIndex] == 10; }

bool Game::isSpareFrame(int frameIndex) const { return rolls[frameIndex] + rolls[frameIndex + 1] == 10; }
