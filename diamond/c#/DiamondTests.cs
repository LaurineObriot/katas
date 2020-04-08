using FluentAssertions;
using Xunit;

namespace DiamondKata
{
    public class DiamondTest
    {
        private readonly Diamond diamond = new Diamond();

        [Fact]
        public void Diamond_A()
        {
            var diamondA = diamond.Of('A');
            diamondA.Should().ContainInOrder("A");
        }

        [Fact]
        public void Diamond_B()
        {
            var diamondB = diamond.Of('B');
            diamondB.Should().ContainInOrder("_A_",
                                             "B_B",
                                             "_A_");
        }

        [Fact]
        public void Diamond_C()
        {
            var diamondC = diamond.Of('C');
            diamondC.Should().ContainInOrder("__A__",
                                             "_B_B_",
                                             "C___C",
                                             "_B_B_",
                                             "__A__");
        }

        [Fact]
        public void Diamond_D()
        {
            var diamondD = diamond.Of('D');
            diamondD.Should().ContainInOrder("___A___",
                                             "__B_B__",
                                             "_C___C_",
                                             "D_____D",
                                             "_C___C_",
                                             "__B_B__",
                                             "___A___");
        }
    }
}
