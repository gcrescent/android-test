package application.me.androidtest.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import application.me.androidtest.BaseActivity
import application.me.androidtest.model.Promo
import application.me.androidtest.R
import application.me.androidtest.model.PromoInteractor
import application.me.androidtest.presenter.PromoDetailPresenter
import application.me.androidtest.toPx
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_promo_detail.*

class PromoDetailActivity : BaseActivity(), PromoDetailPresenter.View {

    companion object {

        private const val INTENT_PROMO = "promo"

        fun newIntent(context: Context, promo: Promo): Intent {
            val intent = Intent(context, PromoDetailActivity::class.java)
            intent.putExtra(INTENT_PROMO, promo)
            return intent
        }
    }

    private lateinit var presenter: PromoDetailPresenter
    private lateinit var promo: Promo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_detail)

        presenter = PromoDetailPresenter(this, PromoInteractor(this))
        promo = intent.getParcelableExtra(INTENT_PROMO)

        presenter.getPromoDetail(promo)
    }

    override fun setupView(promo: Promo) {
        Glide.with(this)
            .load(promo.image)
            .centerCrop()
            .into(promoImage)

        promoName.text = promo.name
        promoValidDate.text = promo.validDate
        voucherCode.text = promo.voucherCode

        copyVoucherCode.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(getString(R.string.voucher_code), promo.voucherCode)
            clipboard.primaryClip = clip
            Toast.makeText(this, R.string.voucher_code_copied, Toast.LENGTH_SHORT).show()
        }

        promo.terms?.let {
            for (term in it) {
                val tableRow = TableRow(this)
                val lp = TableLayout.LayoutParams()
                lp.setMargins(0, 8.toPx, 0, 0)
                tableRow.layoutParams = lp

                val bullet = TextView(this)
                bullet.text = getString(R.string.interpunct)
                bullet.width = 8.toPx

                val text = TextView(this)
                text.text = term

                tableRow.addView(bullet)
                tableRow.addView(text)
                promoTerms.addView(tableRow)
            }
        }
    }
}