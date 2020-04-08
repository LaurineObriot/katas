using FluentAssertions;
using Xunit;

namespace DiamondKata
{
    public class EnumerableExtensionsTests
    {
        [Fact]
        public void Mirror_Should_Not_Add_Extra_Item_Given_Empty_Enumerable()
        {
            var items = new string[0];
            var result = items.Mirror();
            result.Should().BeEmpty();
        }

        [Fact]
        public void Mirror_Should_Not_Add_Extra_Item_Given_Single_Item_Enumerable()
        {
            var items = new[] { "a" };
            var result = items.Mirror();
            result.Should().ContainInOrder("a");
        }

        [Fact]
        public void Mirror_Should_Repeat_First_Item_At_The_End_Given_Dual_Item_Enumerable()
        {
            var items = new[]
            {
                "a",
                "b"
            };
            var result = items.Mirror();
            result.Should().ContainInOrder(
                "a",
                "b",
                "a");
        }

        [Fact]
        public void Mirror_Should_Repeat_In_Reversed_Order_All_Items_But_The_Last_One_Given_Any_Other_Enumerable()
        {
            var items = new[]
            {
                "a",
                "b",
                "c"
            };
            var result = items.Mirror();
            result.Should().ContainInOrder(
                "a",
                "b",
                "c",
                "b",
                "a");
        }
    }
}
