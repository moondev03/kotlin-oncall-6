package oncall.utils

fun <T> retryOnException(action: () -> T, onException: (Exception) -> Unit): T {
    while (true) {
        try {
            return action.invoke()
        } catch (e: IllegalArgumentException) {
            onException(e)
        }
    }
}