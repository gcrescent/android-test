package application.me.androidtest.presenter

class PaymentConfirmationPresenter(private val view: View) {

    fun doPayment(pin: String) {
        when {
            pin.isBlank() -> view.showError("Please input your PIN")
            pin.length < 6 -> // verify PIN length
                view.showError("PIN must be 6 digit")
            else -> view.paymentSuccess()
        }
    }

    interface View {
        fun showError(error: String)
        fun paymentSuccess()
    }
}