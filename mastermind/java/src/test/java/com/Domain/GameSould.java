package com.domain;

import com.Exception.EmptyRoundListException;
import com.Exception.GameAlreadyFinishedException;
import com.Exception.PawnsSelectionAlreadyCompleteException;
import com.Exception.PawnsSelectionUncompletedException;
import com.comparator.PawnSelectionsComparator;
import com.enums.Color;
import com.enums.State;
import com.enums.Winner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    @Mock
    private PawnSelectionsComparator pawnSelectionsComparator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void create_computer_selection_with_four_pawns_and_list_of_rounds_with_given_size_when_game_starts() {
        //Given
        Game aGame;
        int numberOfRounds = 10;

        //When
        aGame = new Game(numberOfRounds);

        //Then
        assertThat(aGame.getComputerPawns()).hasSize(4);
        assertThat(aGame.getRounds().getMaxSize()).isEqualTo(numberOfRounds);
        assertThat(aGame.getState()).isEqualTo(State.STARTED);
        assertThat(aGame.getWinner()).isEqualTo(Winner.UNKNOWN);
    }

    @Test
    public void add_pawn_to_user_pawns_with_given_color() {
        //Given
        Game aGame = new Game(1);

        //When
        boolean result = aGame.addUserPawn(Color.ROUGE);

        //Then
        assertThat(aGame.getUserPawns()).hasSize(1);
        assertThat(result).isTrue();
    }

    @Test
    public void throw_exception_when_selection_is_already_complete () {
        //Given
        Game aGame = new Game(1);

        //When
        aGame.addUserPawn(Color.ROUGE);
        aGame.addUserPawn(Color.ROUGE);
        aGame.addUserPawn(Color.ROUGE);
        aGame.addUserPawn(Color.ROUGE);
        thrown.expect(PawnsSelectionAlreadyCompleteException.class);
        thrown.expectMessage("Pawns selection already complete!");
        aGame.addUserPawn(Color.ROUGE);

        //Then
    }

    @Test
    public void remove_pawn_at_given_index_from_user_pawns() {
        //Given
        Game aGame = new Game(1);
        Color aColor = Color.ROUGE;
        Color anotherColor = Color.BLEU;
        aGame.addUserPawn(aColor);
        aGame.addUserPawn(anotherColor);

        //When
        aGame.removeUserPawn(0);

        //Then
        assertThat(aGame.getUserPawns()).hasSize(1);
        assertThat(aGame.getUserPawns().get(0)).isEqualTo(new Pawn(anotherColor));
    }

    @Test
    public void add_last_winning_round_when_user_wins() throws PawnsSelectionUncompletedException, EmptyRoundListException, GameAlreadyFinishedException {
        //Given
        Game aGame = new Game(1);
        aGame.getUserPawns().addAll(aGame.getComputerPawns());

        //When
        aGame.finishRound();
        Round lastRound = aGame.getRounds().getCurrentRound();

        //Then
        assertThat(lastRound.getUserPawns()).isEqualTo(aGame.getComputerPawns());
        assertThat(lastRound.getCorrectlyPlacedPawns()).isEqualTo(4);
        assertThat(lastRound.getMisplacedPawns()).isEqualTo(0);
        assertThat(aGame.getState()).isEqualTo(State.FINISHED);
        assertThat(aGame.getWinner()).isEqualTo(Winner.USER);
    }

    @Test
    public void add_next_round_when_user_selection_is_not_valid() throws PawnsSelectionUncompletedException, EmptyRoundListException, GameAlreadyFinishedException {
        //Given
        Game aGame = new Game(2);
        aGame.getComputerPawns().forEach(pawn -> aGame.addUserPawn(pawn.getColor().next()));

        Field pawnSelectionsComparatorField = ReflectionUtils.findField(Game.class, "pawnSelectionsComparator");
        ReflectionUtils.makeAccessible(pawnSelectionsComparatorField);
        ReflectionUtils.setField(pawnSelectionsComparatorField, aGame, this.pawnSelectionsComparator);
        when(this.pawnSelectionsComparator.apply(any(), any()))
                .thenReturn(new PawnSelectionsComparison(2, 2));

        //When
        aGame.finishRound();
        Round lastRound = aGame.getRounds().getCurrentRound();

        //Then
        assertThat(aGame.getRounds().size()).isEqualTo(1);
        assertThat(lastRound.getUserPawns()).isNotEqualTo(aGame.getComputerPawns());
        assertThat(lastRound.getCorrectlyPlacedPawns()).isEqualTo(2);
        assertThat(lastRound.getMisplacedPawns()).isEqualTo(2);
        assertThat(aGame.getState()).isEqualTo(State.ONGOING);
        assertThat(aGame.getWinner()).isEqualTo(Winner.UNKNOWN);
        assertThat(aGame.getUserPawns().isEmpty()).isTrue();
    }

    @Test
    public void set_computer_as_winner() throws PawnsSelectionUncompletedException, EmptyRoundListException, GameAlreadyFinishedException {
        //Given
        Game aGame = new Game(1);
        aGame.getComputerPawns().forEach(pawn -> aGame.addUserPawn(pawn.getColor().next()));

        Field pawnSelectionsComparatorField = ReflectionUtils.findField(Game.class, "pawnSelectionsComparator");
        ReflectionUtils.makeAccessible(pawnSelectionsComparatorField);
        ReflectionUtils.setField(pawnSelectionsComparatorField, aGame, this.pawnSelectionsComparator);
        when(this.pawnSelectionsComparator.apply(any(), any()))
                .thenReturn(new PawnSelectionsComparison(2, 2));


        //When
        aGame.finishRound();
        Round lastRound = aGame.getRounds().getCurrentRound();

        //Then
        assertThat(lastRound.getUserPawns()).isNotEqualTo(aGame.getComputerPawns());
        assertThat(lastRound.getCorrectlyPlacedPawns()).isEqualTo(2);
        assertThat(lastRound.getMisplacedPawns()).isEqualTo(2);
        assertThat(aGame.getState()).isEqualTo(State.FINISHED);
        assertThat(aGame.getWinner()).isEqualTo(Winner.COMPUTER);
    }

    @Test
    public void throw_exception_when_adding_round_and_game_is_finished() throws PawnsSelectionUncompletedException, GameAlreadyFinishedException {
        //Given
        Game aGame = new Game(1);
        aGame.getComputerPawns().forEach(pawn -> aGame.addUserPawn(pawn.getColor().next()));
        aGame.finishRound();

        //When
        aGame.getComputerPawns().forEach(pawn -> aGame.addUserPawn(pawn.getColor()));
        thrown.expect(GameAlreadyFinishedException.class);
        thrown.expectMessage("Game is over!");
        aGame.finishRound();

        //Then
    }

    @Test
    public void throw_exception_when_selection_is_not_complete () throws PawnsSelectionUncompletedException, GameAlreadyFinishedException {
        //Given
        Game aGame = new Game(1);
        aGame.addUserPawn(Color.BLEU);

        //When
        thrown.expect(PawnsSelectionUncompletedException.class);
        thrown.expectMessage("User selection is not complete");
        aGame.finishRound();

        //Then
    }
}
