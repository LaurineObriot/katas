using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankAccount
{
    public class Account
    {
        private List<StatementLine> statementLines = new List<StatementLine>();
        Money balance = new Money(0m, Currency.EUR());

        public Money Balance
        {
            get
            {
                return balance;
            }
        }

        public void Deposit(Money quantity)
        {
            balance = balance + quantity;
            statementLines.Add(new StatementLine(DateTime.Now, quantity, balance));
        }

        public void Withdrawal(Money quantity)
        {
            balance = balance - quantity;
            statementLines.Add(new StatementLine(
                DateTime.Now, 
                new Money(-quantity.Amount, quantity.Currency),
                balance));
        }

        public override string ToString()
        {
            StringBuilder stringBuilder = new StringBuilder();

            statementLines.ForEach(line =>
            {
                stringBuilder.AppendLine(line.ToString());
            });

            return stringBuilder.ToString();
        }
    }
}
