using FluentAssertions;
using Xunit;

namespace DiamondKata
{
    public class StringExtensionsTests
    {
        [Fact]
        public void Mirror_Should_Not_Add_Extra_Character_Given_Empty_String()
        {
            var result = string.Empty.Mirror();
            result.Should().BeEmpty();
        }

        [Fact]
        public void Mirror_Should_Not_Add_Extra_Character_Given_Single_Item_String()
        {
            var result = "a".Mirror();
            result.Should().Be("a");
        }

        [Fact]
        public void Mirror_Should_Repeat_First_Character_At_The_End_Given_Dual_Character_String()
        {
            var result = "ab".Mirror();
            result.Should().Be("aba");
        }

        [Fact]
        public void Mirror_Should_Repeat_In_Reversed_Order_All_Characters_But_The_Last_One_Given_Any_Other_String()
        {
            var result = "abc".Mirror();
            result.Should().Be("abcba");
        }
    }
}
