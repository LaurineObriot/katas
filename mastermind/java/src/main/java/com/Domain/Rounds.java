package com.domain;

import com.Exception.EmptyRoundListException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
public class Rounds extends ArrayList<Round> {
    @Getter private int maxSize;

    public boolean add(Round round) {
        return this.size() < maxSize && super.add(round);
    }

    Round getCurrentRound() throws EmptyRoundListException {
        if (this.size() == 0)
            throw new EmptyRoundListException("Can't get current round because no round exist yet!");
        else
            return this.get(this.size() - 1);
    }

    boolean createNextRound(PawnsSelection userPawns, int correctlyPlacedPawns, int misplacedPawns) {
        return this.add(new Round(userPawns, correctlyPlacedPawns, misplacedPawns));
    }
}
