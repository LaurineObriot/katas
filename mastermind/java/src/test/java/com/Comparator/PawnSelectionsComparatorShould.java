package com.comparator;

import com.domain.Pawn;
import com.domain.PawnSelectionsComparison;
import com.domain.PawnsSelection;
import com.enums.Color;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PawnSelectionsComparatorShould {

    @Test
    @Parameters
    public void give_correctly_placed_and_misplaced_pawns_from_two_selections(final Color computerColor1, final Color computerColor2, final Color computerColor3, final Color computerColor4, final Color userColor1, final Color userColor2, final Color userColor3, final Color userColor4, final Integer correctlyPlacedPawns, final Integer misplacedPawns) {
        //Given
        PawnSelectionsComparator pawnSelectionsComparator = new PawnSelectionsComparator();
        PawnsSelection aComputerPawnsSelection = new PawnsSelection();
        aComputerPawnsSelection.add(new Pawn(computerColor1));
        aComputerPawnsSelection.add(new Pawn(computerColor2));
        aComputerPawnsSelection.add(new Pawn(computerColor3));
        aComputerPawnsSelection.add(new Pawn(computerColor4));
        PawnsSelection aUserPawnsSelection = new PawnsSelection();
        aUserPawnsSelection.add(new Pawn(userColor1));
        aUserPawnsSelection.add(new Pawn(userColor2));
        aUserPawnsSelection.add(new Pawn(userColor3));
        aUserPawnsSelection.add(new Pawn(userColor4));

        //When
        PawnSelectionsComparison result = pawnSelectionsComparator.apply(aComputerPawnsSelection, aUserPawnsSelection);

        //Then
        assertThat(result.getCorrectlyPlacedPawns()).isEqualTo(correctlyPlacedPawns);
        assertThat(result.getMisplacedPawns()).isEqualTo(misplacedPawns);
    }

    private Object[] parametersForGive_correctly_placed_and_misplaced_pawns_from_two_selections() {
        return new Object[][]{
                {Color.ROUGE, Color.NOIR, Color.VERT, Color.ORANGE, Color.BLEU, Color.JAUNE, Color.ORANGE, Color.ROUGE, 0, 2},
                {Color.ROUGE, Color.NOIR, Color.VERT, Color.ORANGE, Color.ROUGE, Color.NOIR, Color.JAUNE, Color.BLEU, 2, 0},
                {Color.ROUGE, Color.NOIR, Color.VERT, Color.ORANGE, Color.ROUGE, Color.NOIR, Color.ORANGE, Color.VERT, 2, 2},
                {Color.ROUGE, Color.NOIR, Color.VERT, Color.ORANGE, Color.ROUGE, Color.NOIR, Color.ROUGE, Color.VERT, 2, 1},
                {Color.ROUGE, Color.NOIR, Color.VERT, Color.ROUGE, Color.ROUGE, Color.NOIR, Color.ROUGE, Color.VERT, 2, 2},
        };
    }
}
