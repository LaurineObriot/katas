#include "minunit.h"
#include "diamond.h"

MU_TEST(test_single_row_diamond) {
    diamond('A');
    mu_assert_int_eq(1, max_output);
    mu_assert_string_eq("A", output[0]);
}

MU_TEST(test_three_row_diamond) {
    diamond('B');
    mu_assert_int_eq(3, max_output);
    mu_assert_string_eq(" A ", output[0]);
    mu_assert_string_eq("B B", output[1]);
    mu_assert_string_eq(" A ", output[2]);
}

MU_TEST_SUITE(test_suite) {
	MU_RUN_TEST(test_single_row_diamond);
	MU_RUN_TEST(test_three_row_diamond);
}
int main(int argc, char *argv[]) {
	MU_RUN_SUITE(test_suite);
	MU_REPORT();
	return 0;
}
