package oncall.controller

import oncall.utils.retryOnException
import oncall.view.InputView
import oncall.view.OutputView

class MainController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun serviceStart() {
        doInputWeekDayAndDays()
        doInputWeekDayWorkOrder()
        doInputWeekDayWorkOrder()
    }

    private fun doInputWeekDayAndDays() {
        retryOnException {
            val input = inputView.inputTargetMonthAndDays()
        }
    }

    private fun doInputWeekDayWorkOrder() {
        retryOnException {
            val input = inputView.inputWeekDayWorkOrder()
        }
    }

    private fun doInputHolidayWorkOrder() {
        retryOnException {
            val input = inputView.inputHolidayWorkOrder()
        }
    }

    private fun <T> retryOnException(action: () -> T) {
        retryOnException(
            action = action,
            onException = { exception -> outputView.printErrorMessage(exception.message.toString()) }
        )
    }
}