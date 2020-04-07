package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class Round {
    @Getter private PawnsSelection userPawns;
    @Getter private int correctlyPlacedPawns;
    @Getter private int misplacedPawns;
}
