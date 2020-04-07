package com.comparator;

import com.domain.PawnSelectionsComparison;
import com.domain.PawnsSelection;
import io.vavr.collection.Stream;

import java.util.function.BiFunction;

public class PawnSelectionsComparator implements BiFunction<PawnsSelection, PawnsSelection, PawnSelectionsComparison> {
    @Override
    public PawnSelectionsComparison apply(PawnsSelection computerPawns, PawnsSelection userPawns) {
        return new PawnSelectionsComparison(getCorrectlyPlacedPawns(computerPawns, userPawns), getMisplacedPawns(computerPawns, userPawns));
    }

    private Integer getCorrectlyPlacedPawns(PawnsSelection computerPawns, PawnsSelection userPawns) {
        return Stream.of(computerPawns.toArray()).zip(Stream.of(userPawns.toArray()))
                .count(pawnsTuple -> pawnsTuple._1.equals(pawnsTuple._2));
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private Integer getMisplacedPawns(PawnsSelection computerPawns, PawnsSelection userPawns) {
        return Stream.of(computerPawns.toArray()).zip(Stream.of(userPawns.toArray()))
                .count(pawnsTuple -> userPawns.contains(pawnsTuple._1) && !pawnsTuple._1.equals(pawnsTuple._2));
    }
}
