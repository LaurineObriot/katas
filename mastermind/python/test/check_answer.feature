Feature: answer checking

  Scenario: test player guess checking
     Given we have a guess and a code, both without repeated numbers
      when we test it against the answer
      then we have the correct element count for both lists without repeated numbers

     Given we have a guess with repeated numbers and a game code without repeated numbers
      when we test it against the answer
      then we have the correct element count for a guess with repeated numbers

     Given we have a guess and a code, both with repeated numbers
      when we test it against the answer
      then we have the correct element count for both list with repeated numbers

  Scenario Outline: Professor tests
     Given the testing "<guess>" and "<code>" given by the professor
      when we test it against the answer
      then we have the correct <element count>

      Examples:
          | guess | code | element count |
          | 1111  | 1234 | (1, 3)        |
          | 1234  | 1111 | (1, 0)        |
          | 1213  | 1334 | (1, 2)        |
