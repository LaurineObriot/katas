using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankAccount
{
    public class Money
    {
        private decimal amount;
        private Currency currency;

        public Money(decimal amount, Currency currency)
        {
            if (currency == null) throw new ArgumentNullException("currency is required");

            this.amount = amount;
            this.currency = currency;
        }

        public decimal Amount
        {
            get
            {
                return amount;
            }
        }

        public Currency Currency
        {
            get
            {
                return currency;
            }
        }

        public override String ToString()
        {
            return $"{amount.ToString()} {currency.ToString()}";
        }

        public override bool Equals(object obj)
        {
            var other = obj as Money;
            if (other == null)
                return base.Equals(obj);

            if (!object.Equals(this.amount, other.amount)) return false;
            return currency.Equals(other.currency);
        }

        public override int GetHashCode()
        {
            int result = amount.GetHashCode();
            result = 31 * result + currency.GetHashCode();
            return result;
        }

        public static Money operator +(Money my, Money other)
        {
            return new Money(my.Amount + other.Amount, my.currency);
        }

        public static Money operator -(Money my, Money other)
        {
            return new Money(my.Amount - other.Amount, my.currency);
        }
    }
}
