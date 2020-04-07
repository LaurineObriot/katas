# maker of the codes. Assumed to be a computer player for now
class Codemaker
  def initialize(display, code_colors)
    @display = display
    @code_colors = code_colors
  end

  def code
    @code = 4.times.collect { @code_colors.sample }
  end
end
