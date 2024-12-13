package oncall.model

import oncall.model.Days.Companion.nextDay

class WorkSchedule(month: Int, day: Days) {

    private val schedules = mutableListOf<Schedule>()

    init {
        createSchedule(month, day)
    }

    private fun createSchedule(month: Int, day: Days) {
        var currentDay = day

        repeat(if (month != 2) 31 else 28) { t ->
            currentDay = if (t == 0) day else currentDay.nextDay()

            schedules.add(
                Schedule(month = month, date = t + 1, day = currentDay)
            )
        }
    }

    fun addWorkerToSchedule(weekdayWorkOrder: List<String>, holidayWorkOrder: List<String>) {
        var weekdayCount = 0
        var holidayCount = 0

        schedules.forEach { schedule ->
            if (schedule.isHoliday) {
                schedule.setWorkerName(holidayWorkOrder[holidayCount])
                holidayCount = if (holidayWorkOrder.size == holidayCount + 1) 0 else holidayCount + 1
            } else {
                schedule.setWorkerName(weekdayWorkOrder[weekdayCount])
                weekdayCount = if (weekdayWorkOrder.size == weekdayCount + 1) 0 else weekdayCount + 1
            }
        }
    }

    fun fixScheduleOrder(weekdayWorkOrder: List<String>, holidayWorkOrder: List<String>) {
        repeat(schedules.size - 1) { i ->
            val schedule = schedules[i]
            val nextSchedule = schedules[i + 1]
            val changeSchedule = if(schedules.size >= i + 2) schedules[i + 2] else null

            if (schedule.worker == nextSchedule.worker) {
                if (nextSchedule.isHoliday) {
                    val nextWorker = holidayWorkOrder.indexOf(nextSchedule.worker)
                        .let { holidayWorkOrder[if (holidayWorkOrder.size >= it + 1) it + 1 else 0] }
                    changeSchedule?.setWorkerName(nextSchedule.worker ?: "")
                    nextSchedule.setWorkerName(nextWorker)
                } else {
                    val nextWorker = weekdayWorkOrder.indexOf(nextSchedule.worker)
                        .let { weekdayWorkOrder[if (weekdayWorkOrder.size >= it + 1) it + 1 else 0] }
                    changeSchedule?.setWorkerName(nextSchedule.worker ?: "")
                    nextSchedule.setWorkerName(nextWorker)
                }
            }
        }
    }

    fun getWorkSchedule(): List<Schedule> = schedules.toList()
}