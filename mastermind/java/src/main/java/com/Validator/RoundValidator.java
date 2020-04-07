package com.validator;

import com.Exception.PawnsSelectionUncompletedException;
import com.domain.PawnsSelection;
import io.vavr.control.Validation;

public class RoundValidator {

    public Validation<String, String> validateRound(PawnsSelection computerPawns, PawnsSelection userPawns) throws PawnsSelectionUncompletedException {
        return computerPawns.equals(userPawns)
                ? Validation.valid("User wins!")
                : Validation.invalid("User selection does not match computer selection");
    }
}
