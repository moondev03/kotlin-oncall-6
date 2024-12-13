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
        doInputHolidayWorkOrder()
        onCall.addWorkerToSchedule()
        printWorkSchedule()
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
            onCall.inputToWeekdayWorkOrder(input)
        }
    }

    private fun doInputHolidayWorkOrder() {
        retryOnException {
            val input = inputView.inputHolidayWorkOrder()
            onCall.inputToHolidayWorkOrder(input)
            onCall.validateWorker()
        }
    }

    private fun printWorkSchedule() {
        val workSchedule = onCall.getWorkScheduleResult()
        outputView.printResult(workSchedule)
    }

    private fun <T> retryOnException(action: () -> T) {
        retryOnException(
            action = action,
            onException = { exception ->
                val message = exception.message.toString()
                outputView.printErrorMessage(message.ifBlank { INVALID_INPUT_EXCEPTION.message })
            }
        )
    }
}