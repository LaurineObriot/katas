package com.domain;

import com.enums.Color;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnsSelectionShould {

    @Test
    public void return_false_when_add_and_size_is_four() {
        //Given
        PawnsSelection aPawnSelection = new PawnsSelection();
        aPawnSelection.add(new Pawn(Color.getRandomColor()));
        aPawnSelection.add(new Pawn(Color.getRandomColor()));
        aPawnSelection.add(new Pawn(Color.getRandomColor()));
        aPawnSelection.add(new Pawn(Color.getRandomColor()));

        //When
        boolean result = aPawnSelection.add(new Pawn(Color.getRandomColor()));

        //Then
        assertThat(result).isFalse();
        assertThat(aPawnSelection).hasSize(4);
    }

    @Test
    public void return_true_when_adding_and_size_is_not_four () {
        //Given
        PawnsSelection aPawnSelection = new PawnsSelection();

        //When
        boolean result = aPawnSelection.add(new Pawn(Color.getRandomColor()));

        //Then
        assertThat(result).isTrue();
        assertThat(aPawnSelection).hasSize(1);
    }
}
