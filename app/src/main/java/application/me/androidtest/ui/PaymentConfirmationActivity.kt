package application.me.androidtest.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import application.me.androidtest.BaseActivity
import application.me.androidtest.model.Product
import application.me.androidtest.R
import application.me.androidtest.formatMoney
import application.me.androidtest.view.CustomDialog
import application.me.androidtest.presenter.PaymentConfirmationPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_payment_confirmation.*

class PaymentConfirmationActivity : BaseActivity(), PaymentConfirmationPresenter.View {

    companion object {

        private const val DO_PAYMENT = 11

        private const val INTENT_OPERATOR_LOGO = "operatorLogo"
        private const val INTENT_PHONE_NUMBER = "phoneNumber"
        private const val INTENT_PRODUCT = "product"

        fun newIntent(context: Context, operatorLogo: Int?, phoneNumber: String, product: Product) : Intent {
            val intent = Intent(context, PaymentConfirmationActivity::class.java)
            intent.putExtra(INTENT_OPERATOR_LOGO, operatorLogo)
            intent.putExtra(INTENT_PHONE_NUMBER, phoneNumber)
            intent.putExtra(INTENT_PRODUCT, product)
            return intent
        }
    }

    private lateinit var presenter: PaymentConfirmationPresenter
    private var operatorLogo: Int = 0
    private lateinit var phoneNumber: String
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_confirmation)

        presenter = PaymentConfirmationPresenter(this)

        operatorLogo = intent.getIntExtra(INTENT_OPERATOR_LOGO, 0)
        phoneNumber = intent.getStringExtra(INTENT_PHONE_NUMBER)
        product = intent.getParcelableExtra(INTENT_PRODUCT)

        setupView()
    }

    private fun setupView() {
        Glide.with(this)
            .load(operatorLogo)
            .into(pulsaOperatorLogo)

        pulsaPhoneNumber.text = phoneNumber

        productName.text = product.name
        productAmount.text = product.amount.formatMoney
        adminFee.text = 0.formatMoney
        totalAmount.text = product.amount.formatMoney

        pay.setOnClickListener {
            presenter.doPayment(pinInput.text.toString())
        }
    }

    override fun showError(error: String) {
        CustomDialog(this, error).show()
    }

    override fun paymentSuccess() {
        startActivityForResult(PaymentDetailsActivity.newIntent(this, operatorLogo, phoneNumber, product), DO_PAYMENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DO_PAYMENT && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}