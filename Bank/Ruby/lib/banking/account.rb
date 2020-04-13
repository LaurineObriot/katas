module Banking
  class Account
    class NoFundsError < StandardError; end

    attr_reader :bank

    def initialize(bank, nbr, balance = 0)
      @bank = bank
      @nbr = nbr
      @balance = Float(balance)
    end

    def ==(other)
      to_s == other.to_s
    end

    def to_s
      "#{@bank}-#{@nbr}"
    end

    def withdrawal(amt)
      amt = Float(amt)
      fail NoFundsError.new("balance is insuffcient") if no_funds?(amt)
      @balance -= amt
    end

    def deposit(amt)
      @balance += Float(amt)
    end

    def balance
      @balance.round(2)
    end

    def report
      "#{to_s} BALANCE: #{balance}"
    end

    private def no_funds?(amt)
      @balance < amt
    end
  end
end
