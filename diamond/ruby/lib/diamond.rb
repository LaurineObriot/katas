class Diamond
  END_OF_LINE = "\n"

  def initialize(character)
    @character = character
    @final_letter = FinalLetter.new(character)
  end

  def draw
    output = ''
    line_letters.each do |letter|
      output << letter.line + END_OF_LINE
    end
    output
  end

  private

  def line_letters
    letters = Hash.new(MiddleLetter)
    letters['A'] = InitialLetter
    letters[@character] = FinalLetter

    line = []
    @final_letter.path.each do |letter|
      line << letters[letter].new(letter, size)
    end
    line
  end

  def size
    @final_letter.position
  end
end

module Letter
  ALPHABET = ('A'..'Z').to_a

  def initialize(letter, size = 0)
    @letter = letter
    @size = size
  end

  def position
    ALPHABET.index(@letter)
  end

  def line
    left_side + left_side[0..-2].reverse
  end

  def blanks
    " " * @size
  end
end

class InitialLetter
  include Letter

  def left_side
    blanks + @letter
  end
end

class MiddleLetter
  include Letter

  def left_side
    left = " " * (@size + 1)
    location = @size - position
    left[location] = @letter
    left
  end
end

class FinalLetter
  include Letter

  def path
    right = ALPHABET[0..position] - [ALPHABET[0..position].last]
    ALPHABET[0..position] + right.reverse
  end

  def left_side
    @letter + blanks
  end
end
