using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankAccount
{
    public class Currency
    {
        private readonly string value;

        private Currency(string value)
        {
            if (value == null)
                throw new ArgumentNullException("value");
           
            this.value = value;
        }

        public static Currency EUR()
        {
            return new Currency("EUR");
        }

        public static Currency USD()
        {
            return new Currency("USD");
        }

        public override string ToString()
        {
            return value;
        }

        public override bool Equals(object obj)
        {
            var other = obj as Currency;
            if (other == null)
                return base.Equals(obj);

            return object.Equals(this.value, other.value);
        }

        public override int GetHashCode()
        {
            return this.value.GetHashCode();
        }
    }
}
