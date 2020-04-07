COLORS = {
  red: :red,
  green: :green,
  blue: :blue,
  yellow: :yellow,
  purple: :magenta,
  orange: :red,
  black: :black,
  white: :white
}.freeze

ICONS = {
  red: '🔴',
  green: '💚',
  blue: '💙',
  yellow: '💛',
  purple: '💜',
  orange: '🍊',
  blank: '❌',
  black: '⚫️',
  white: '⚪️',
  hidden: '❓'
}.freeze

# class to control input from and output to the user
class Display
  def initialize(code_colors, debug)
    @code_colors = code_colors
    @debug = debug
    @colorize = false
    return unless Gem::Specification.find_all_by_name('colorize').any?

    require 'colorize'
    @colorize = true
  end

  def get_guess(guess_count)
    guess_input = guess_input(guess_count)
    guess = parse_guess_input(guess_input)
    if guess.length != 4
      output("please enter 4 colours #{color_letters}")
      get_guess(guess_count)
    else
      guess
    end
  end

  def play_again?
    input = input('Play again? [yn]')
    %w[y yes].include?(input.downcase)
  end

  def show_code(code)
    output('Code has been chosen!')
    if @debug
      output(pegs_to_icons(code))
    else
      output(4.times.collect { ICONS[:hidden] }.join)
    end
  end

  def show_pegs(guess_pegs, feedback_pegs)
    output(pegs_to_icons(guess_pegs), :print)
    output(' ', :print)
    output(pegs_to_icons(feedback_pegs[0..1]))
    output(' ' * 9, :print)
    output(pegs_to_icons(feedback_pegs[2..3] || []))
  end

  def show_victory(guess_count)
    output "Hooray! You won after #{guess_count} guesses!"
  end

  private

  def pegs_to_icons(pegs)
    pegs.map { |p| ICONS[p] }.join
  end

  def guess_input(guess_count)
    if guess_count.zero?
      output('Guess from 6 colours ', :print)
      output(pegs_to_icons(@code_colors))
      input("#{color_letters} or #{color_numbers}")
    else
      input("guess #{guess_count + 1}")
    end
  end

  def parse_guess_input(guess_input)
    inputs_to_guesses = @code_colors.each_with_index.map do |c, i|
      [[c[0], c], [(i + 1).to_s, c]]
    end.flatten(1).to_h
    matches = guess_input.scan(/[#{inputs_to_guesses.keys.join}]/)
    matches.map { |m| inputs_to_guesses[m] }
  end

  def color_letters
    if @colorize
      @code_colors.map do |c|
        c[0].colorize(color: COLORS[c])
      end.join
    else
      @code_colors.map { |c| c[0] }.join
    end
  end

  def color_numbers
    if @colorize
      @code_colors.each_with_index.map do |c, i|
        (i + 1).to_s.colorize(color: COLORS[c])
      end.join
    else
      @code_colors.enum_with_index.map { |_c, i| (i + 1).to_s }.join
    end
  end

  def input(message)
    output("#{message} >", :print)
    print(' ')
    gets.strip
  end

  def output(message, function = :puts)
    send(function, message)
  end
end
