using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankAccount.Test
{
    public class MoneyBuilder
    {
        private decimal amount;
        private Currency currency;

        public MoneyBuilder EUR(string amount)
        {
            this.currency = Currency.EUR();
            this.amount = decimal.Parse(amount);
            return this;
        }

        public MoneyBuilder USD(string amount)
        {
            this.currency = Currency.USD();
            this.amount = decimal.Parse(amount);
            return this;
        }

        public Money build()
        {
            return new Money(amount, currency);
        }
    }
}
