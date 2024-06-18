package dev.gdsc_dut.android_xml_base_project.core

enum class LoadingStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    ERROR;

    val isLoading: Boolean
        get() = this == LOADING

    val isSuccess: Boolean
        get() = this == SUCCESS

    val isError: Boolean
        get() = this == ERROR

    val isInitial: Boolean
        get() = this == INITIAL
}


