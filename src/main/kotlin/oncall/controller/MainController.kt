package oncall.controller

import oncall.model.OnCall
import oncall.utils.OnCallException.INVALID_INPUT_EXCEPTION
import oncall.utils.retryOnException
import oncall.view.InputView
import oncall.view.OutputView

class MainController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val onCall: OnCall
) {

    fun serviceStart() {
        doInputWeekDayAndDays()
        doInputWeekDayWorkOrder()
        doInputWeekDayWorkOrder()
    }

    private fun doInputWeekDayAndDays() {
        retryOnException {
            val input = inputView.inputTargetMonthAndDays()
            onCall.inputToTarget(input)
            onCall.createWorkSchedule()
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
            onException = { exception ->
                outputView.printErrorMessage(exception.message ?: INVALID_INPUT_EXCEPTION.message)
            }
        )
    }
}