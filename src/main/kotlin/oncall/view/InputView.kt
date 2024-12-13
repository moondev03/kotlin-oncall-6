package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.view.MessageConstants.INPUT_HOLIDAY_SEQUENCE_MESSAGE
import oncall.view.MessageConstants.INPUT_TARGET_MONTH_MESSAGE
import oncall.view.MessageConstants.INPUT_WEEKDAY_SEQUENCE_MESSAGE

class InputView {

    fun inputTargetMonthAndDays() : String {
        print(INPUT_TARGET_MONTH_MESSAGE)
        return Console.readLine()
    }

    fun inputWeekDayWorkOrder(): String {
        print(INPUT_WEEKDAY_SEQUENCE_MESSAGE)
        return Console.readLine()
    }

    fun inputHolidayWorkOrder(): String {
        print(INPUT_HOLIDAY_SEQUENCE_MESSAGE)
        return Console.readLine()
    }
}