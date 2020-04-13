module Banking
  class Transfer
    Specs = Struct.new(:limit, :commission, :failure_rate) do
      def to_s
        "limit: #{limit < 0 ? "none" : limit}, commission: #{commission}, failure_rate: #{failure_rate}"
      end
    end

    INTRABANK = Specs.new(-1, 0, 0)
    INTERBANK = Specs.new(1000, 5, 30)
    TIME_FMT = "%Y-%m-%d %H:%M:%S"

    class LimitExceededError < StandardError; end
    class StochasticError < StandardError; end
    class SameAccountError < StandardError; end

    attr_reader :src, :target, :succeeded

    def initialize(src, target, amt, specs, time = Time.now)
      @src = src
      @target = target
      @amt = Float(amt)
      @specs = specs
      @time = time
      @succeeded = false
    end

    def to_s
      "Transfer(src: #{@src}, target: #{@target}, amount: #{@amt}, succeeded: #{@succeeded}, time: #{time})"
    end

    def ==(other)
      to_s == other.to_s
    end

    def call
      return if @succeeded
      fail SameAccountError.new("cannot transfer from same account") if same_account?
      fail LimitExceededError.new("limit of #{@specs.limit} exceeded") if overflow?
      fail StochasticError.new("failure of transfer, try again") if failure?
      commit! && success && record
    end

    def time
      @time.strftime(TIME_FMT)
    end

    private def success
      @succeeded = true
    end

    private def commit!
      @src.withdrawal(@amt + @specs.commission) && @target.deposit(@amt)
    end

    private def record
      [@src.bank, @target.bank].uniq { |b| b.to_s }.each { |b| b << self }
    end

    private def overflow?
      @specs.limit > 0 && @amt > @specs.limit 
    end

    private def failure?
      @specs.failure_rate > 0 && rand(100) <= @specs.failure_rate
    end

    private def same_account?
      @src == @target
    end
  end
end
