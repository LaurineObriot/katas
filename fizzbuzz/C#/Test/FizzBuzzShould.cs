using KataBizzBuzz.Code;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace KataBizzBuzz.Test
{
    public class FizzBuzzShould
    {
        [Fact]
        public void Generate_First_Number_If_MaxNumber_Is_One()
        {
            Assert.Equal(new String[] {"1"},FizzBuzz.GenerateOutput(1));
        }

        [Fact]
        public void Generating_The_First_Two_Numbers_If_MaxNumber_Is_Two()
        {
            Assert.Equal(new String[] { "1","2" }, FizzBuzz.GenerateOutput(2));
        }

        [Fact]
        public void Generating_The_First_Two_Numbers_And_Fizz_If_MaxNumber_Is_Three()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz" }, FizzBuzz.GenerateOutput(3));
        }

        [Fact]
        public void Generating_The_First_Four_Numbers_If_MaxNumber_Is_Four()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4" }, FizzBuzz.GenerateOutput(4));
        }

        [Fact]
        public void Generating_The_First_Five_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Five()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4","Buzz" }, FizzBuzz.GenerateOutput(5));
        }

        [Fact]
        public void Generating_The_First_Six_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Six()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz" }, FizzBuzz.GenerateOutput(6));
        }

        [Fact]
        public void Generating_The_First_Seven_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Seven()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz","7" }, FizzBuzz.GenerateOutput(7));
        }

        [Fact]
        public void Generating_The_First_Eight_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Eight()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7","8" }, FizzBuzz.GenerateOutput(8));
        }

        [Fact]
        public void Generating_The_First_Nine_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Nine()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7","8","Fizz" }, FizzBuzz.GenerateOutput(9));
        }

        [Fact]
        public void Generating_The_First_Ten_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Ten()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz" }, FizzBuzz.GenerateOutput(10));
        }

        [Fact]
        public void Generating_The_First_Eleven_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Eleven()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11"}, FizzBuzz.GenerateOutput(11));
        }

        [Fact]
        public void Generating_The_First_Twelve_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Twelve()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11" , "Fizz"}, FizzBuzz.GenerateOutput(12));
        }

        [Fact]
        public void Generating_The_First_Thirteen_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Thirteen()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13" }, FizzBuzz.GenerateOutput(13));
        }

        [Fact]
        public void Generating_The_First_Fourteen_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Fourteen()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14" }, FizzBuzz.GenerateOutput(14));
        }

        [Fact]
        public void Generating_The_First_Fiveteen_Numbers_With_Bizz_And_Buzz_If_MaxNumber_Is_Fiveteen()
        {
            Assert.Equal(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz" }, FizzBuzz.GenerateOutput(15));
        }
    }
}
