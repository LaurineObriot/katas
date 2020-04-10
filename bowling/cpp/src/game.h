#ifndef BOWLINGGAME_GAME_H
#define BOWLINGGAME_GAME_H

#include <vector>

class Game {
    std::vector<int> rolls;
    bool isSpareFrame(int frameIndex) const;
    bool isStrikeFrame(int frameIndex) const;
    int spareBonus(int frameIndex) const;
    int strikeBonus(int frameIndex) const;
    int sumOfKnockedPinsInFrame(int frameIndex);

public:

    void roll(int pins);

    int score();
};

#endif //BOWLINGGAME_GAME_H
