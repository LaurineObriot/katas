import StringCalculator from '../src/string-calculator';

test('Add empty string', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("");

    // Assert
    expect(sum).toEqual(0);
});

test('Add single string number', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("1");

    // Assert
    expect(sum).toEqual(1);
});

test('Add two string number delimited with comma', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("1,2");

    // Assert
    expect(sum).toEqual(3);
});

test('Add unknown amount of string numbers delimited with commas', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("1,2,3,4,5,6,7,8,9,10");

    // Assert
    expect(sum).toEqual(55);
});

test('Add string numbers can be delimited by new line', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("1\n2,3");

    // Assert
    expect(sum).toEqual(6);
});

test('Add string numbers with default delimiter set', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    let sum = calculator.Add("//;\n1;2;3");

    // Assert
    expect(sum).toEqual(6)
});

test('Negative integer is not allowed', () =>
{
    // Arrange
    let calculator = new StringCalculator();

    // Act
    function func(){
        calculator.Add("1,-2,-3");
    };

    // Assert
    expect(func).toThrowError(new Error('Negative integer is not allowed'));
});
