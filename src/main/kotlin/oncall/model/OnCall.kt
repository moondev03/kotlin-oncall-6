package oncall.model

import oncall.utils.OnCallException.INVALID_INPUT_DAY_EXCEPTION
import oncall.utils.OnCallException.INVALID_INPUT_MONTH_EXCEPTION

class OnCall {

    private var targetMonth: Int = 0
    private lateinit var targetDay: Days
    private lateinit var workSchedule: WorkSchedule

    fun inputToTarget(input: String) {
        val splitInput = input.split(DELIMITER).map { it.trim() }
        require(splitInput.size == 2)

        setTargetMonth(splitInput[0])
        setTargetDay(splitInput[1])
    }

    private fun setTargetMonth(rawTargetMonth: String) {
        val month = rawTargetMonth.toIntOrNull()
        require(month != null) { INVALID_INPUT_MONTH_EXCEPTION.message }
        require(month in 1..12) { INVALID_INPUT_MONTH_EXCEPTION.message }
        targetMonth = month
    }

    private fun setTargetDay(rawTargetDay: String) {
        val day = Days.toDay(rawTargetDay)
        require(day != null) { INVALID_INPUT_DAY_EXCEPTION.message }
        targetDay = day
    }

    fun createWorkSchedule() {
        workSchedule = WorkSchedule(targetMonth, targetDay)
    }

    companion object {
        private const val DELIMITER = ","
    }
}