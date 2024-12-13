package oncall.model

enum class Days(private val koValue: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    companion object {
        fun toDay(p: String): Days? = Days.entries.find { it.koValue == p }

        fun Days.isHoliday(): Boolean = when (this) {
            SATURDAY, SUNDAY -> true
            else -> false
        }

        fun Days.nextDay(): Days {
            val index = Days.entries.indexOf(this).let { if(it == -1) 0 else it }
            val nextIndex = if(index + 1 > Days.entries.size - 1) 0 else index + 1
            return Days.entries[nextIndex]
        }
    }
}