package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PawnSelectionsComparison {
    @Getter private Integer correctlyPlacedPawns;
    @Getter private Integer misplacedPawns;
}
