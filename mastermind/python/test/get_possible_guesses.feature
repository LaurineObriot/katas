Feature: impossible guesses removal

  Scenario: removing guesses from the set
     Given I have an answer for a player guess
      when I get the list of possible codes for this guess and the answer
      then I have the expected list of possible codes
