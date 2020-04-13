using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankAccount
{
    public class StatementLine
    {
        public StatementLine (DateTime date, Money quantity, Money balance)
        {
            if (date == null)
                throw new ArgumentNullException("date is required");

            if (quantity == null)
                throw new ArgumentNullException("quantity is required");

            if (balance == null)
                throw new ArgumentNullException("balance is required");

            this.Date = date;
            this.Quantity = quantity;
            this.Balance = balance;
        }

        public DateTime Date { get; }
        public Money Quantity { get; }
        public Money Balance { get; }

        public override String ToString()
        {
            return $"Date: {Date.ToString("yyyy-MM-dd")} Quantity: {Quantity} Balance: {Balance}";
        }
    }
}
