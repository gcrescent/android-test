package application.me.androidtest.helper

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val spaceInPx: Int, private val orientation: Orientation) : RecyclerView.ItemDecoration() {

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position: Int = parent.getChildLayoutPosition(view) ?: 0

        if (position == state.itemCount.minus(1) ?: 0) {
            if (orientation == Orientation.HORIZONTAL) {
                outRect.right = 0
            } else {
                outRect.bottom = 0
            }
        } else {
            if (orientation == Orientation.HORIZONTAL) {
                outRect.right = spaceInPx
            } else {
                outRect.bottom = spaceInPx
            }
        }
    }
}