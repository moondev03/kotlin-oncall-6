package oncall.model

import oncall.model.Days.Companion.isHoliday
import oncall.utils.OnCallException.INVALID_INPUT_DAY_EXCEPTION
import oncall.utils.OnCallException.INVALID_INPUT_MONTH_EXCEPTION

class OnCall {

    private var targetMonth: Int = 0
    private lateinit var targetDay: Days
    private lateinit var workSchedule: WorkSchedule
    private var weekdayWorkOrder = listOf<String>()
    private var holidayWorkOrder = listOf<String>()

    fun inputToTarget(input: String) {
        val splitInput = input.split(DELIMITER).map { it.trim() }
        require(splitInput.size == 2) { "" }

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

    fun inputToWeekdayWorkOrder(input: String) {
        val splitInput = input.split(DELIMITER).map { it.trim() }
        validateWorkOrder(splitInput)
        weekdayWorkOrder = splitInput
    }

    fun inputToHolidayWorkOrder(input: String) {
        val splitInput = input.split(DELIMITER).map { it.trim() }
        validateWorkOrder(splitInput)
        holidayWorkOrder = splitInput
    }

    private fun validateWorkOrder(workOrder: List<String>) {
        require(workOrder.size in 5..35) { "근무자는 최소 5명, 최대 35명입니다." }
        require(workOrder.all { it.length in 1..5 }) { "근무자 닉네임은 최대 5글자입니다. " }
        require(workOrder.distinct().size == workOrder.size) { "각 근무자 닉네임은 유일해야 합니다." }
    }

    fun validateWorker(){
        require(weekdayWorkOrder.sorted() == holidayWorkOrder.sorted()) { "비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 합니다." }
    }

    fun addWorkerToSchedule() {
        workSchedule.addWorkerToSchedule(weekdayWorkOrder, holidayWorkOrder)
        workSchedule.fixScheduleOrder(weekdayWorkOrder, holidayWorkOrder)
    }

    fun getWorkScheduleResult(): List<String> =
        workSchedule.getWorkSchedule().map { schedule ->
            SCHEDULE_PRINT_FORMAT.format(
                schedule.month,
                schedule.date,
                schedule.day.koValue + if(schedule.isHoliday && !schedule.day.isHoliday()) "(휴일)" else "",
                schedule.worker
            )
        }


    companion object {
        private const val DELIMITER = ","
        private const val SCHEDULE_PRINT_FORMAT = "%s월 %s일 %s %s"
    }
}