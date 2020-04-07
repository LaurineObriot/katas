# compares code to guess and calculates black and white pegs
class Feedback
  attr_reader :pegs

  def initialize(code, guess)
    @code = code
    @guess = guess
    @guess_feedbacks = guess.map { |g| { guess: g, feedback_peg: nil } }
    add_black_pegs
    add_white_pegs
    @pegs = @guess_feedbacks.map { |fg| fg[:feedback_peg] }.reject(&:nil?).sort
  end

  ##
  # a black peg is given for a correct colour in the correct position
  def add_black_pegs
    @guess_feedbacks.each_with_index.map do |gf, index|
      guess_color = gf[:guess]
      gf[:feedback_peg] = :black if @code[index] == guess_color
      gf
    end
  end

  ##
  # a white peg is given for a correct colour in the wrong position
  def add_white_pegs
    @guess_feedbacks.map! do |gf|
      guess_color = gf[:guess]
      feedback_peg = gf[:feedback_peg]
      # only consider awarding a white if a black hasn't already been awarded
      gf[:feedback_peg] = :white if !feedback_peg && award_white?(guess_color)
      gf
    end
  end

  ##
  # Only award a number of feedback pegs per colour guess up to the number of
  # matches in the code. e.g. if the code is oboo and the guess is bboo the
  # feedback pegs should be XBBB, not WBBB
  #
  # https://en.wikipedia.org/wiki/Mastermind_(board_game)#Gameplay_and_rules
  #
  def award_white?(guess_color)
    pegs_in_code = @code.select { |i| i == guess_color }.count
    pegs_scored = @guess_feedbacks
                  .select do |gf|
                    gf[:feedback_peg] && (gf[:guess] == guess_color)
                  end.count
    pegs_scored < pegs_in_code
  end

  ##
  # no peg is given for an incorrect colour
  def add_blanks
    @guess_feedbacks.map! do |gf|
      gf[:feedback_peg] = :blank unless gf[:feedback_peg]
      gf
    end
  end
end
