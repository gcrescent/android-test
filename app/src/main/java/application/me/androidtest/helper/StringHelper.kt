package application.me.androidtest.helper

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan

class StringHelper {

    companion object {

        fun makeColorSpannable(text: CharSequence, colorText: String?, color: Int): SpannableString {
            val string = SpannableString(text)
            colorText?.let {
                string.setSpan(
                    ForegroundColorSpan(color), text.toString().indexOf(it),
                    text.toString().indexOf(it) + it.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            return string
        }
    }
}