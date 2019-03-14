package application.me.androidtest.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import application.me.androidtest.BaseActivity
import application.me.androidtest.R
import application.me.androidtest.formatMoney
import application.me.androidtest.helper.StringHelper
import application.me.androidtest.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_payment_details.*

class PaymentDetailsActivity : BaseActivity() {

    companion object {

        private const val INTENT_OPERATOR_LOGO = "operatorLogo"
        private const val INTENT_PHONE_NUMBER = "phoneNumber"
        private const val INTENT_PRODUCT = "product"

        fun newIntent(context: Context, operatorLogo: Int?, phoneNumber: String, product: Product): Intent {
            val intent = Intent(context, PaymentDetailsActivity::class.java)
            intent.putExtra(INTENT_OPERATOR_LOGO, operatorLogo)
            intent.putExtra(INTENT_PHONE_NUMBER, phoneNumber)
            intent.putExtra(INTENT_PRODUCT, product)
            return intent
        }
    }

    private var operatorLogo: Int = 0
    private lateinit var phoneNumber: String
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        showToolbar()
        setActionTitle(getString(R.string.payment_details))
        showCloseButton()

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

        invoiceNotice.text = StringHelper.makeColorSpannable(
            getString(R.string.invoice_notice), getString(R.string.invoice_notice_highlighted),
            ContextCompat.getColor(this, R.color.colorPrimary)
        )

        confirm.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }
}