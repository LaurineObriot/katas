require "helper"

describe Banking::Transfer do
  let(:exceeded) { Banking::Transfer::Specs.new(10, 0, 0) }
  let(:alwaysfail) { Banking::Transfer::Specs.new(-1, 0, 100) }
  let(:bank) { Banking::Bank.new("Acme", Dir.mktmpdir) } 
  let(:accounts) { [Banking::Account.new(bank, 666, 5000), Banking::Account.new(bank, 777, 1_000_000)]}
  let(:transfer) { Banking::Transfer.new(accounts[0], accounts[1], 499.99, Banking::Transfer::INTRABANK, Time.new(2018, 10, 29)) }

  it "must be represented as a string" do
    transfer.to_s.must_equal "Transfer(src: ACME-666, target: ACME-777, amount: 499.99, succeeded: false, time: 2018-10-29 00:00:00)"
  end

  it "must support equality" do
    transfer.must_equal Banking::Transfer.new(accounts[0], accounts[1], 499.99, Banking::Transfer::INTRABANK, Time.new(2018, 10, 29))
    transfer.wont_equal Banking::Transfer.new(accounts[0], accounts[1], 999.99, Banking::Transfer::INTRABANK, Time.new(2018, 10, 29))
  end
  
  it "must fail if transfer on same account" do
    -> { Banking::Transfer.new(accounts[0], accounts[0], 11, Banking::Transfer::INTRABANK).call }.must_raise Banking::Transfer::SameAccountError
  end
  
  it "must fail for exceeded limit" do
    -> { Banking::Transfer.new(accounts[0], accounts[1], 11, exceeded).call }.must_raise Banking::Transfer::LimitExceededError
  end

  it "must fail stochastically" do
    -> { Banking::Transfer.new(accounts[0], accounts[1], 11, alwaysfail).call }.must_raise Banking::Transfer::StochasticError
  end

  it "must call transfer successfully" do
    transfer.call
    transfer.src.balance.must_equal(4500.01)
    transfer.target.balance.must_equal(1000499.99)
    transfer.succeeded.must_equal true
  end

  it "must prevent transefer on succeeded operation" do
    transfer.call
    transfer.call.must_be_nil
    transfer.src.balance.must_equal(4500.01)
    transfer.target.balance.must_equal(1000499.99)
  end

  it "must store transfers on banks" do
    transfer.call
    transfer.src.bank.transfers.size.must_equal 1
    transfer.target.bank.transfers[0].must_equal transfer
  end
end
