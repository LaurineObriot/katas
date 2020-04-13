require "banking/transfer"

module Banking
  class Agent
    def initialize(src, target, amt, out = STDOUT, transfer_cls = Transfer)
      @src = src
      @target = target
      @amt = amt
      @out = out
      @transfer_cls = transfer_cls
      @specs = specs
      @retries = 0
    end

    def call
      balance_report
      commit!
      history_report
      balance_report
    end

    private def commit!
      amounts.each do |amt|
        transfer(amt).call && @amt -= amt
      end
    rescue @transfer_cls::StochasticError
      retry if (@retries += 1) < @specs.failure_rate
    end

    private def balance_report
      [@src, @target].each do |account|
        @out.puts account.report, ""
      end
    end

    private def history_report
      [@src.bank, @target.bank].uniq { |b| b.to_s }.each do |bank|
        @out.puts bank.history, ""
      end
    end

    private def transfer(amt)
      @transfer_cls.new(@src, @target, amt, @specs)
    end

    private def specs
      return @transfer_cls::INTRABANK if @src.bank == @target.bank
      @transfer_cls::INTERBANK
    end

    private def amounts
      n, rest = @amt.divmod(@specs.limit)
      Array.new(n) { @specs.limit }.tap do |slices|
        slices << rest unless rest.zero?
      end
    end
  end
end
