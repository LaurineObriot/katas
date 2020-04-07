class StringCalculator{
    constructor() { }

    Add(input){
        try{
            if (input === "") return 0;

            let integers = this.GetIntegers(input);

            let total = 0;
            integers.forEach(integer => {
                this.ValidateInteger(integer);
                total += integer;
            });

            return total;
        }
        catch(err){
            throw err.message;
        }
    }

    GetIntegers(input){
        let delimiters = this.GetDelimiters(input);
        let hasDefaultDelimiter = this.CheckDefaultDelimiter(input);

        if(hasDefaultDelimiter){
            input = input.split('\n')[1];
        }

        let stringNumbers = input.split(delimiters);

        let integers = [];
        stringNumbers.forEach(stringNumber =>
        {
            integers.push(parseInt(stringNumber));
        });

        return integers;
    }

    GetDelimiters(input){
        let delimiters = [',', '\n'];
        let hasDefaultDelimiter = this.CheckDefaultDelimiter(input);

        if(hasDefaultDelimiter){
            delimiters.push(input.substring(2,3));
        }

        return new RegExp(delimiters.join('|', 'g'));
    }

    CheckDefaultDelimiter(input){
        return input.substring(0, 2) === "//"
            ? true
            : false;
    }

    ValidateInteger(integer){
        if(integer < 0)
            throw new Error('Negative integer is not allowed');
    }
};

module.exports = StringCalculator;
