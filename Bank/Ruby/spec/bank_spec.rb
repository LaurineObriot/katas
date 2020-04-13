require "helper"

describe Banking::Bank do
  let(:bank) { Banking::Bank.new("Acme", Dir.mktmpdir) } 

  it "must be represented as a string" do
    bank.to_s.must_equal "ACME"
  end

  it "must support equality" do
    bank.must_equal Banking::Bank.new("Acme")
    bank.wont_equal Banking::Bank.new("McDuck")
  end

  it "must add transfers" do
    bank << "transfer 1"
    bank << "transfer 2"
    bank.transfers.size.must_equal 2
  end
end
