using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace BankAccount.Test
{
    public class AccountShould
    {
        [Fact]
        public void have_zero_as_balance_first_time()
        {
            Money expectedBalance = EUR("0");

            Account account = new Account();

            Money currentBalance = account.Balance;

            Assert.Equal(expectedBalance, currentBalance);
        }

        [Fact]
        public void have_balance_increased_after_a_deposit()
        {
            Money expectedBalance = EUR("10");

            Account account = new Account();

            account.Deposit(EUR("10"));

            Assert.Equal(expectedBalance, account.Balance);
        }

        [Fact]
        public void have_balance_decreased_after_a_deposit()
        {
            Money expectedBalance = EUR("0");

            Account account = new Account();

            account.Deposit(EUR("10"));
            account.Withdrawal(EUR("10"));

            Assert.Equal(expectedBalance, account.Balance);
        }

        [Fact]
        public void print_statements()
        {
            string date = DateTime.Now.ToString("yyyy-MM-dd");

            string expectedStatements =
                $"Date: {date} Quantity: 15 EUR Balance: 15 EUR\r\n" +
                $"Date: {date} Quantity: -10 EUR Balance: 5 EUR\r\n";

            Account account = new Account();

            account.Deposit(EUR("15"));
            account.Withdrawal(EUR("10"));

            String currentStatements = account.ToString();

            Assert.Equal(expectedStatements, currentStatements);
        }

        public Money EUR(string amount)
        {
            return new MoneyBuilder()
                .EUR(amount)
                .build();
        }
    }
}
