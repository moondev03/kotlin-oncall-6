package oncall.utils

enum class OnCallException(val message: String) {
    INVALID_INPUT_EXCEPTION("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_INPUT_MONTH_EXCEPTION("월 입력이 유효하지 않습니다. 다시 입력해 주세요."),
    INVALID_INPUT_DAY_EXCEPTION("요일 입력이 유효하지 않습니다. 다시 입력해 주세요."),
}