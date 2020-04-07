# breaker of the codes. Assumed to be a human player for now
class Codebreaker
  def initialize(display)
    @display = display
  end

  def guess(guess_count)
    @display.get_guess(guess_count)
  end
end
