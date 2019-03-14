package application.me.androidtest.presenter

import application.me.androidtest.model.Product
import application.me.androidtest.model.Promo
import application.me.androidtest.model.PulsaInteractor

class PulsaFragmentPresenter(private val view: View) {

    private val interactor = PulsaInteractor()

    fun fetchPulsa(phoneNumber: String) {
        if (phoneNumber.length >= 4) {
            view.setOperator(interactor.getOperator(phoneNumber))
            interactor.getPulsa(phoneNumber, object : PulsaInteractor.OnFetchSuccessListener {
                override fun onSuccess(products: List<Product>) {
                    view.showProducts(products)
                }
            })
        } else {
            view.hideOperatorAndProducts()
        }
    }

    fun fetchPaketData(phoneNumber: String) {
        if (phoneNumber.length >= 4) {
            view.setOperator(interactor.getOperator(phoneNumber))
            interactor.getPaketData(phoneNumber, object : PulsaInteractor.OnFetchSuccessListener {
                override fun onSuccess(products: List<Product>) {
                    view.showProducts(products)
                }
            })
        } else {
            view.hideOperatorAndProducts()
        }
    }

    fun fetchPromotions() {
        view.showPromotions(interactor.fetchPromotions())
    }

    interface View {
        fun setOperator(operatorLogo: Int?)
        fun showProducts(products: List<Product>)
        fun hideOperatorAndProducts()
        fun showPromotions(promos: List<Promo>)
    }
}