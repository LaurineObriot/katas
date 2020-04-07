require './lib/round.rb'
require './lib/feedback.rb'
require './lib/codemaker.rb'
require './lib/codebreaker.rb'
require './lib/display.rb'

CODE_COLORS = %i[red green blue yellow purple orange].freeze

# control the game, continuous rounds of code setting and guessing
class Mastermind
  def initialize(debug)
    @display = Display.new(CODE_COLORS, debug)
    @codemaker = Codemaker.new(@display, CODE_COLORS)
    @codebreaker = Codebreaker.new(@display)
  end

  def play
    round = Round.new(@display, @codemaker, @codebreaker)
    round.play
    play if @display.play_again?
  end
end

debug = ARGV[0] == 'debug'
ARGV.clear
m = Mastermind.new(debug)
m.play
