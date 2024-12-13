package oncall.view

class OutputView {

    fun printErrorMessage(message: String) = println(ERROR_MESSAGE_PREFIX + message)

    fun printResult(results: List<String>) {
        println()
        println(results.joinToString("\n"))
    }

    companion object {
        private const val ERROR_MESSAGE_PREFIX = "[ERROR] "
    }
}