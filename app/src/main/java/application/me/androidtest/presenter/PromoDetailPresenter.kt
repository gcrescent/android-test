package application.me.androidtest.presenter

import application.me.androidtest.model.Promo
import application.me.androidtest.model.PromoInteractor

class PromoDetailPresenter(private val view: View, private val interactor: PromoInteractor) {

    fun getPromoDetail(promo: Promo) {
        interactor.fetchPromoDetail(promo, object : PromoInteractor.OnFetchListener {
            override fun onSuccess(promo: Promo) {
                view.setupView(promo)
            }
        })
    }

    interface View {
        fun setupView(promo: Promo)
    }
}