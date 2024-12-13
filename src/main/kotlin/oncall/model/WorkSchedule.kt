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
            currentDay = if(t == 0) day else currentDay.nextDay()

            schedules.add(
                Schedule(month = month, date = t + 1, day = currentDay)
            )
        }
    }
}