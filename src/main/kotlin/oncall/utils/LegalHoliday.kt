package oncall.utils

enum class LegalHoliday(val month: Int, val date: Int) {
    NEW_YEAR(1, 1),
    INDEPENDENCE_DAY(3, 1),
    CHILDREN_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    LIBERATION_DAY(8, 15),
    FOUNDATION_DAY(10, 3),
    HANGUL_DAY(10, 9),
    CHRISTMAS(12, 25);

    companion object {
        fun isHoliday(month: Int, date: Int): Boolean {
            return entries.find { it.month == month && it.date == date } != null
        }
    }
}