
# 设置最小的 cmake 版本
cmake_minimum_required(VERSION 3.4.1)

# 添加库
add_library( # Sets the name of the library.
        hellocpp-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        helloCpp.cpp)

# 生成链接库.
target_link_libraries( # Specifies the target library.
        hellocpp-lib

        # Links the target library to the log library
        # included in the NDK.
        log)