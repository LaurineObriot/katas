#include <gtest\gtest.h>
#include <gmock\gmock.h>
#include "FizzBuzzListGenerator.h"
#include "FizzBuzzNumber.h"

using namespace testing;
using namespace std;

class TestFixture : public Test {

public:
	FizzBuzzListGenerator *generator;
	FizzBuzzNumber* n;

	void SetUp() override {
		generator = new FizzBuzzListGenerator();
		n = NULL;
	}

	void TearDown() override {
		delete generator;
		delete n;
	}
};

TEST_F(TestFixture, FizzTrue) {

	n = new FizzBuzzNumber(3);

	ASSERT_THAT(n->isFizz(), Eq(true));
}

TEST_F(TestFixture, FizzFalse) {

	n = new FizzBuzzNumber(4);

	ASSERT_THAT(n->isFizz(), Eq(false));
}

TEST_F(TestFixture, BuzzTrue) {

	n = new FizzBuzzNumber(5);

	ASSERT_THAT(n->isBuzz(), Eq(true));
}

TEST_F(TestFixture, BuzzFalse) {

	n = new FizzBuzzNumber(6);

	ASSERT_THAT(n->isBuzz(), Eq(false));
}

TEST_F(TestFixture, FizzBuzzTrue) {

	n = new FizzBuzzNumber(15);

	ASSERT_THAT(n->isFizz() && n->isBuzz(), Eq(true));
}

TEST_F(TestFixture, FizzBuzzFalse) {

	n = new FizzBuzzNumber(16);

	ASSERT_THAT(n->isFizz() && n->isBuzz(), Eq(false));
}

TEST_F(TestFixture, NumberString) {

	n = new FizzBuzzNumber(1);

	ASSERT_THAT(n->getFizzBuzz(), Eq(string("1")));
}

TEST_F(TestFixture, FizzString) {

	n = new FizzBuzzNumber(3);

	ASSERT_THAT(n->getFizzBuzz(), Eq(string("Fizz")));
}

TEST_F(TestFixture, BuzzString) {

	n = new FizzBuzzNumber(5);

	ASSERT_THAT(n->getFizzBuzz(), Eq(string("Buzz")));
}

TEST_F(TestFixture, FizzBuzzString) {

	n = new FizzBuzzNumber(15);

	ASSERT_THAT(n->getFizzBuzz(), Eq(string("FizzBuzz")));
}

TEST_F(TestFixture, FizzBuzzList) {

	ASSERT_THAT(generator->generateListWithThisManyInts(5), Eq(string("1\n2\nFizz\n4\nBuzz\n")));
}
