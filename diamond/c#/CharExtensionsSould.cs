using FluentAssertions;
using Xunit;

namespace DiamondKata
{
    public class CharExtensionsShould
    {
        [Fact]
        public void Return_SequenceDownToA_Given_Letter_A() =>
            'A'.SequenceDownToA()
               .Should()
               .Be("A");

        [Fact]
        public void Return_SequenceDownToA_Given_Letter_B() =>
            'B'.SequenceDownToA()
               .Should()
               .Be("BA");

        [Fact]
        public void Return_SequenceDownToA_Given_Letter_C() =>
            'C'.SequenceDownToA()
               .Should()
               .Be("CBA");

        [Fact]
        public void Return_SequenceDownToA_Given_Letter_D() =>
            'D'.SequenceDownToA()
               .Should()
               .Be("DCBA");
    }
}
