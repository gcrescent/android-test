package application.me.androidtest.model

import application.me.androidtest.R
import application.me.androidtest.formatMoney

class PulsaInteractor {

    fun getOperator(phoneNumber: String): Int? {
        // hardcoded because no api call
        // only check some operator

        var number = phoneNumber
        number = number.replace("-", "")
        number = number.replace(" ", "")
        when {
            number.startsWith("+62") -> {
                number = number.substring(3);
                number = "0$number"
            }
            number.startsWith("62") -> {
                number = number.substring(2);
                number = "0$number"
            }
            number.startsWith("8") -> number = "0$number"
        }

        return when {
            number.startsWith("0811") -> R.drawable.telkomsel
            number.startsWith("0812") -> R.drawable.telkomsel
            number.startsWith("0813") -> R.drawable.telkomsel
            number.startsWith("0821") -> R.drawable.telkomsel
            number.startsWith("0822") -> R.drawable.telkomsel
            number.startsWith("0823") -> R.drawable.telkomsel
            number.startsWith("0852") -> R.drawable.telkomsel
            number.startsWith("0853") -> R.drawable.telkomsel
            number.startsWith("0851") -> R.drawable.telkomsel

            number.startsWith("0855") -> R.drawable.indosat
            number.startsWith("0856") -> R.drawable.indosat
            number.startsWith("0857") -> R.drawable.indosat
            number.startsWith("0858") -> R.drawable.indosat
            number.startsWith("0815") -> R.drawable.indosat
            number.startsWith("0816") -> R.drawable.indosat

            number.startsWith("0817") -> R.drawable.xl
            number.startsWith("0818") -> R.drawable.xl
            number.startsWith("0819") -> R.drawable.xl
            number.startsWith("0859") -> R.drawable.xl
            number.startsWith("0877") -> R.drawable.xl
            number.startsWith("0878") -> R.drawable.xl

            number.startsWith("0896") -> R.drawable.three
            number.startsWith("0897") -> R.drawable.three
            number.startsWith("0898") -> R.drawable.three
            number.startsWith("0899") -> R.drawable.three

            number.startsWith("0881") -> R.drawable.smart
            number.startsWith("0882") -> R.drawable.smart
            number.startsWith("0883") -> R.drawable.smart
            number.startsWith("0884") -> R.drawable.smart
            number.startsWith("0885") -> R.drawable.smart
            number.startsWith("0886") -> R.drawable.smart
            number.startsWith("0887") -> R.drawable.smart

            else -> R.drawable.indosat // fallback if no match
        }

    }

    fun getPulsa(phoneNumber: String, listener: OnFetchSuccessListener) {
        // hardcoded because no api call
        val products = ArrayList<Product>()
        products.add(Product("Pulsa " + 25000.formatMoney, 25000))
        products.add(Product("Pulsa " + 50000.formatMoney, 50000))
        products.add(Product("Pulsa " + 100000.formatMoney, 100000))
        products.add(Product("Pulsa " + 150000.formatMoney, 150000))
        products.add(Product("Pulsa " + 200000.formatMoney, 200000))
        listener.onSuccess(products)
    }

    fun getPaketData(phoneNumber: String, listener: OnFetchSuccessListener) {
        // hardcoded because no api call
        val products = ArrayList<Product>()
        products.add(Product("Paket data 5GB", 25000))
        products.add(Product("Paket data 10GB", 50000))
        products.add(Product("Paket data 15GB", 100000))
        products.add(Product("Paket data 20GB", 150000))
        products.add(Product("Paket data 25GB", 200000))
        listener.onSuccess(products)
    }

    fun fetchPromotions(): List<Promo> {
        val promotions = ArrayList<Promo>()
        var promo = Promo()
        promo.image = R.drawable.promo_1
        promotions.add(promo)
        promo = Promo()
        promo.image = R.drawable.promo_2
        promotions.add(promo)
        promo = Promo()
        promo.image = R.drawable.promo_3
        promotions.add(promo)
        return promotions
    }

    interface OnFetchSuccessListener {
        fun onSuccess(products: List<Product>)
    }
}