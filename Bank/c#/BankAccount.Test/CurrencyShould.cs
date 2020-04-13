using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace BankAccount.Test
{
    public class CurrencyShould
    {
        [Fact]
        public void be_equal_for_two_instances_of_the_same_currency()
        {
            Currency currency1 = Currency.EUR();
            Currency currency2 = Currency.EUR();

            Assert.Equal(currency1, currency2);
        }

        [Fact]
        public void be_not_equal_for_two_different_instances_of_currency()
        {
            Currency currency1 = Currency.EUR();
            Currency currency2 = Currency.USD();

            Assert.NotEqual(currency1, currency2);
        }
    }
}
