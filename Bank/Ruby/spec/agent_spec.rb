require "helper"

describe Banking::Agent do
  let(:acme) { Banking::Bank.new("Acme", Dir.mktmpdir) } 
  let(:mcduck) { Banking::Bank.new("McDuck", Dir.mktmpdir) } 
  let(:accounts) { [Banking::Account.new(acme, "JIM", 50000), Banking::Account.new(mcduck, "EMMA", 70000)]}
  let(:out) { StringIO.new }

  it "must execute transfer and procide report" do
    agent = Banking::Agent.new(accounts[0], accounts[1], 4650, out)
    agent.call
    output = out.string.split("\n").reject(&:empty?)
    output[0].must_equal "ACME-JIM BALANCE: 50000.0"
    output[1].must_equal "MCDUCK-EMMA BALANCE: 70000.0"
    output[-2].must_equal "ACME-JIM BALANCE: 45325.0"
    output[-1].must_equal "MCDUCK-EMMA BALANCE: 74650.0"
  end
end
