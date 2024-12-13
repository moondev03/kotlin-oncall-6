package oncall

import oncall.controller.MainController
import oncall.view.InputView
import oncall.view.OutputView

fun main() {
    MainController(
        inputView = InputView(),
        outputView = OutputView()
    ).serviceStart()
}
