Feature: best guess calculation

  Scenario: retrieving next best guess
     Given a list of possible guesses and a list of unused guesses
      when I solicit the next best guess
      then I get the expected guess
