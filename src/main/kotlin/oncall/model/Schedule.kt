package oncall.model

import oncall.model.Days.Companion.isHoliday
import oncall.utils.LegalHoliday

data class Schedule(
    val month: Int,
    val date: Int,
    val day: Days,
    var isHoliday: Boolean = false,
    var worker: String? = null
) {
    init {
        require(month in 1..12) { "month는 1~12 사이의 정수여야 합니다." }
        require(date in 1..if (month != 2) 31 else 28) { "date는 1~31 사이의 정수여야 합니다. (2월은 28일까지)" }

        checkIsHoliday()
    }

    private fun checkIsHoliday() {
        val isHoliday = day.isHoliday()
        val isLegalHoliday = LegalHoliday.isHoliday(month, date)
        this.isHoliday = isHoliday || isLegalHoliday
    }

    fun setWorkerName(worker: String) {
        this.worker = worker
    }
}