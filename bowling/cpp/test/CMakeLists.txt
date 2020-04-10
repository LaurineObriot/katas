add_executable(unittests bowling_score_test.cpp ../src/game.h ../src/game.cpp)
target_link_libraries(unittests gtest_main)
