using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace BankAccount.Test
{
    public class MoneyShould
    {
        [Fact]
        public void throw_argument_null_exception_if_create_with_null_value()
        {
            Assert.Throws<ArgumentNullException>(() => new Money(0m,null));
        }

        [Fact]
        public void be_equal_for_two_instances_of_the_same_money()
        {
            Money money1 = EUR("5"); 
            Money money2 = EUR("5");

            Assert.Equal(money1, money2);
        }

        [Fact]
        public void be_not_equal_for_two_different_instances_of_money()
        {
            Money money1 = EUR("5");
            Money money2 = USD("3");

            Assert.NotEqual(money1, money2);
        }

        [Fact]
        public void realize_add_oprations()
        {
            Money expectedResult = EUR("8");

            Money money1 = EUR("5");
            Money money2 = USD("3");

            Assert.Equal(expectedResult, money1 + money2);
        }

        [Fact]
        public void realize_subtract_oprations()
        {
            Money expectedResult = EUR("2");

            Money money1 = EUR("5");
            Money money2 = USD("3");

            Assert.Equal(expectedResult, money1 - money2);
        }

        public Money EUR(string amount)
        {
            return new MoneyBuilder()
                .EUR(amount)
                .build();
        }

        public Money USD(string amount)
        {
            return new MoneyBuilder()
                .USD(amount)
                .build();
        }
    }
}
