require "helper"

describe Banking::Account do
  let(:bank) { Banking::Bank.new("Acme", Dir.mktmpdir) } 
  let(:account) { Banking::Account.new(bank, 666, 5000) } 

  it "must be represented as a string" do
    account.to_s.must_equal "ACME-666"
  end

  it "must support equality" do
    account.must_equal Banking::Account.new(bank, 666)
    account.wont_equal Banking::Account.new(bank, 777)
  end

  it "must fail withdrawal for no funds" do
    -> { account.withdrawal(5001) }.must_raise Banking::Account::NoFundsError
  end

  it "must withdrawal the specifeid amount" do
    account.withdrawal(499.99)
    account.balance.must_equal(4500.01)
  end

  it "must deposit the specified amount" do
    account.deposit(42.55)
    account.balance.must_equal(5042.55)
  end
end
