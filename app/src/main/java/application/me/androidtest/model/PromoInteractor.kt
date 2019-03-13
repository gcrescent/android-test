package application.me.androidtest.model

import android.content.Context
import application.me.androidtest.R
import java.util.*

class PromoInteractor(private val context: Context) {

    fun fetchPromoDetail(promo: Promo, listener: OnFetchListener) {
        // hardcoded because no API call
        promo.name = context.getString(R.string.hardcoded_promo_name)
        promo.validDate = context.getString(R.string.hardcoded_promo_valid_date)
        promo.voucherCode = context.getString(R.string.hardcoded_promo_code)
        promo.terms = context.resources.getStringArray(R.array.hardcoded_promo_terms).toList()

        listener.onSuccess(promo)
    }

    interface OnFetchListener {
        fun onSuccess(promo: Promo)
    }
}