package application.me.androidtest.presenter

import application.me.androidtest.model.Product
import application.me.androidtest.model.Promo
import application.me.androidtest.model.PulsaInteractor

class PulsaFragmentPresenter(private val view: View) {

    private val interactor = PulsaInteractor()

    fun checkPhoneNumber(phoneNumber: String) {
        if (phoneNumber.length >= 4) {
            interactor.fetchOperator(phoneNumber, object : PulsaInteractor.OnFetchSuccessListener {
                override fun onSuccess(operatorLogo: Int?, products: List<Product>) {
                    view.setOperator(operatorLogo)
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