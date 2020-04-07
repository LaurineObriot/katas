# controls a single round of play, one code set and broken
class Round
  def initialize(display, codemaker, codebreaker)
    @codebreaker = codebreaker
    @display = display
    @guess_count = 0
    @code_broken = false
    @code = codemaker.code
  end

  def play
    @display.show_code(@code)
    guess until @code_broken
    @display.show_victory(@guess_count)
  end

  def guess
    guess = @codebreaker.guess(@guess_count)
    @guess_count += 1
    feedback_pegs = Feedback.new(@code, guess).pegs
    @display.show_pegs(guess, feedback_pegs)
    @code_broken = true if guess == @code
  end
end
