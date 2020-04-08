using System.Collections.Generic;

namespace DiamondKata
{
    public static class CharExtensions
    {
        public static string SequenceDownToA(this char letter) =>
            EnumerateDownToA(letter).Join();

        private static IEnumerable<char> EnumerateDownToA(char letter)
        {
            yield return letter;

            var previous = letter;
            while (previous != 'A')
            {
                previous = Previous(previous);
                yield return previous;
            }
        }

        private static char Previous(char letter)
        {
            var code = (int)letter;
            return (char)(code - 1);
        }
    }
}
