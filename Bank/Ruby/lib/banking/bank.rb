module Banking
  class Bank
    STORAGE = File.expand_path(File.join(__FILE__, '..', 'data'))

    def initialize(name, storage = STORAGE)
      @name = name
      @transfers = []
      @storage = File.join(storage, "#{@name}.mr")
    end

    def to_s
      @name.upcase
    end

    def ==(other)
      to_s == other.to_s
    end

    def <<(transfer)
      @transfers << transfer
      dump
    end

    def transfers
      Marshal.load(File.binread(@storage))
    end

    def history
      ["#{to_s} TRANSFERS HISTORY"] << transfers.sort_by(&:time).each { |t| t.to_s }
    end

    private def dump
      File.open(@storage, 'wb') { |f| f.write(Marshal.dump(@transfers)) }
    end
  end
end
