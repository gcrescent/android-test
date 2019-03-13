package application.me.androidtest.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Promo() : Parcelable {

    var image: Int = 0

    var name: String? = null

    var validDate: String? = null

    var voucherCode: String? = null

    var terms: List<String>? = null

    constructor(parcel: Parcel) : this() {
        image = parcel.readInt()
        name = parcel.readString()
        validDate = parcel.readString()
        voucherCode = parcel.readString()
        terms = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeString(validDate)
        parcel.writeString(voucherCode)
        parcel.writeStringList(terms)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Promo> {
        override fun createFromParcel(parcel: Parcel): Promo {
            return Promo(parcel)
        }

        override fun newArray(size: Int): Array<Promo?> {
            return arrayOfNulls(size)
        }
    }
}