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
    }
}