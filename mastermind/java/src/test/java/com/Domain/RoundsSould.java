package com.domain;

import com.Exception.EmptyRoundListException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundsShould {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throw_exception_when_list_is_empty() throws EmptyRoundListException {
        //Given
        int maxRounds = 5;
        Rounds aListOfRounds = new Rounds(maxRounds);

        //When
        thrown.expect(EmptyRoundListException.class);
        thrown.expectMessage("Can't get current round because no round exist yet!");
        aListOfRounds.getCurrentRound();

        //Then
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void return_current_round_when_list_is_not_empty_or_full() throws EmptyRoundListException {
        //Given
        int maxRounds = 5;
        Rounds aListOfRounds = new Rounds(maxRounds);
        PawnsSelection aUserPawnsList = new PawnsSelection();
        aListOfRounds.createNextRound(aUserPawnsList, 0, 0);

        //When
        Round result = aListOfRounds.getCurrentRound();

        //Then
        assertThat(result).isNotNull();
        assertThat(aListOfRounds.indexOf(result)).isEqualTo(aListOfRounds.size() - 1);
    }

    @Test
    public void return_false_when_last_round_is_reached() {
        //Given
        int maxRounds = 1;
        Rounds aListOfRounds = new Rounds(maxRounds);
        PawnsSelection aUserPawnsList = new PawnsSelection();
        aListOfRounds.createNextRound(aUserPawnsList, 0, 0);

        //When
        boolean result = aListOfRounds.createNextRound(aUserPawnsList, 0, 0);

        //Then
        assertThat(result).isFalse();
        assertThat(aListOfRounds).hasSize(maxRounds);
    }

    @Test
    public void return_true_when_round_is_added () {
        //Given
        int maxRounds = 1;
        Rounds aListOfRounds = new Rounds(maxRounds);
        PawnsSelection aUserPawnsList = new PawnsSelection();

        //When
        boolean result = aListOfRounds.createNextRound(aUserPawnsList, 0, 0);

        //Then
        assertThat(result).isTrue();
        assertThat(aListOfRounds).hasSize(1);
    }
}
