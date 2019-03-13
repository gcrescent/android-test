package application.me.androidtest.model

import application.me.androidtest.R

class PulsaInteractor {

    fun fetchOperator(phoneNumber: String, listener: OnFetchSuccessListener) {
        // if need network call
        listener.onSuccess(getOperator(phoneNumber), getProducts(phoneNumber))
    }

    private fun getOperator(phoneNumber: String): Int? {
        // hardcoded because no api call
        return R.drawable.indosat
    }

    private fun getProducts(phoneNumber: String): List<Int> {
        // hardcoded because no api call
        val products = ArrayList<Int>()
        products.add(25000)
        products.add(50000)
        products.add(100000)
        products.add(150000)
        products.add(200000)
        return products
    }

    fun fetchPromotions(): List<Promo> {
        val promotions = ArrayList<Promo>()
        val promo = Promo()
        promo.image = R.drawable.promo_1
        promotions.add(promo)
        promo.image = R.drawable.promo_2
        promotions.add(promo)
        return promotions
    }

    interface OnFetchSuccessListener {
        fun onSuccess(operatorLogo: Int?, products: List<Int>)
    }
}