package application.me.androidtest

import android.content.res.Resources
import java.text.NumberFormat
import java.util.*

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.formatNumber: String
    get() = NumberFormat
        .getInstance(Locale.GERMAN)
        .format(this)

val Int.formatMoney: String
    get() = if (this >= 0) "Rp " + this.formatNumber else "- Rp " + Math.abs(this).formatNumber