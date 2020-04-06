import { fizzbuzz } from "./fizzbuzz";

describe("fizzbuzz", () => {
  it("should return the numbers passed in that are not divisible by 3 or 5", () => {
    expect(fizzbuzz(1)).toBe(1);
    expect(fizzbuzz(2)).toBe(2);
    expect(fizzbuzz(4)).toBe(4);
    expect(fizzbuzz(7)).toBe(7);
    expect(fizzbuzz(8)).toBe(8);
    expect(fizzbuzz(11)).toBe(11);
  });

  it("should return 'fizz' if the the number passed in is divisible by 3", () => {
    expect(fizzbuzz(3)).toBe("fizz");
    expect(fizzbuzz(6)).toBe("fizz");
    expect(fizzbuzz(9)).toBe("fizz");
    expect(fizzbuzz(12)).toBe("fizz");
  });

  it("should return 'buzz' if the the number passed in is divisible by 5", () => {
    expect(fizzbuzz(5)).toBe("buzz");
    expect(fizzbuzz(10)).toBe("buzz");
    expect(fizzbuzz(20)).toBe("buzz");
  });

  it("should return 'fizzbuzz' if the the number passed in is divisible by 3 and 5", () => {
    expect(fizzbuzz(15)).toBe("fizzbuzz");
    expect(fizzbuzz(30)).toBe("fizzbuzz");
    expect(fizzbuzz(45)).toBe("fizzbuzz");
  });
});
