package com.domain;

import com.Exception.GameAlreadyFinishedException;
import com.Exception.PawnsSelectionAlreadyCompleteException;
import com.Exception.PawnsSelectionUncompletedException;
import com.comparator.PawnSelectionsComparator;
import com.enums.Color;
import com.enums.State;
import com.enums.Winner;
import com.validator.RoundValidator;
import io.vavr.control.Validation;
import lombok.Getter;

import static io.vavr.API.*;

class Game {
    @Getter private PawnsSelection computerPawns = new PawnsSelection();
    @Getter private PawnsSelection userPawns = new PawnsSelection();
    @Getter private Rounds rounds;
    @Getter private State state = State.STARTED;
    @Getter private Winner winner = Winner.UNKNOWN;
    private RoundValidator roundValidator = new RoundValidator();
    private PawnSelectionsComparator pawnSelectionsComparator = new PawnSelectionsComparator();

    Game(int maxRounds) {
        for (int i = 0; i < 4; i++)
            computerPawns.add(new Pawn(Color.getRandomColor()));
        this.rounds = new Rounds(maxRounds);
    }

    boolean addUserPawn(Color color) {
        if (userPawns.size() == 4)
            throw new PawnsSelectionAlreadyCompleteException("Pawns selection already complete!");
        return userPawns.add(new Pawn(color));
    }

    void removeUserPawn(int index) {
        userPawns.remove(index);
    }

    void finishRound() throws PawnsSelectionUncompletedException, GameAlreadyFinishedException {
        if (state == State.FINISHED)
            throw new GameAlreadyFinishedException("Game is over!");
        if (userPawns.size() < 4)
            throw new PawnsSelectionUncompletedException("User selection is not complete");

        Validation<String, String> validation = roundValidator.validateRound(computerPawns, userPawns);

        Match(validation).of(
                Case($(Validation.valid("User wins!")), () -> run(() -> {
                    rounds.createNextRound(userPawns, 4, 0);
                    state = State.FINISHED;
                    winner = Winner.USER;
                })),
                Case($(Validation.invalid("User selection does not match computer selection")), () -> run(() -> {
                    PawnSelectionsComparison apply = pawnSelectionsComparator.apply(computerPawns, userPawns);
                    rounds.createNextRound(userPawns, apply.getCorrectlyPlacedPawns(), apply.getMisplacedPawns());
                    userPawns = new PawnsSelection();
                    if (rounds.size() == rounds.getMaxSize()) {
                        state = State.FINISHED;
                        winner = Winner.COMPUTER;
                    } else {
                        state = State.ONGOING;
                    }
                }))
        );
    }
}
