package application.me.androidtest.model

import android.os.Parcel
import android.os.Parcelable

class Product() : Parcelable {

    constructor(name: String, amount: Int) : this() {
        this.name = name
        this.amount = amount
    }

    var name: String? = null

    var amount: Int = 0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        amount = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}