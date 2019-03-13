package application.me.androidtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (adapter != null) {
            val childCount = childCount
            if (childCount > 0) {
                var maxHeight = 0
                for (pos in 0 until childCount) {
                    val child = getChildAt(pos)
                    child.measure(widthMeasureSpec, heightMeasureSpec)
                    if (child.measuredHeight > maxHeight) {
                        maxHeight = child.measuredHeight
                    }
                }
                super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(maxHeight, View.MeasureSpec.EXACTLY))
            }
        }
    }
}