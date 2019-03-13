package application.me.androidtest.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Promo() : Parcelable {

    var image: Int = 0

    var name: String? = null

    var startDate: Date? = null

    var endDate: Date? = null

    var voucherCode: String? = null

    var terms: List<String>? = null

    constructor(parcel: Parcel) : this() {
        image = parcel.readInt()
        name = parcel.readString()
        parcel.readLong().let {
            startDate = if (it != -1L) Date(it) else null
        }
        parcel.readLong().let {
            endDate = if (it != -1L) Date(it) else null
        }
        voucherCode = parcel.readString()
        terms = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeLong(startDate?.time ?: -1L)
        parcel.writeLong(endDate?.time ?: -1L)
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