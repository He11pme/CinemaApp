package skillfactory.education.cinemaapp.carousel

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class StartLinearSnapHelper : LinearSnapHelper() {

    lateinit var mVerticalHelper: OrientationHelper
    lateinit var mHorizontalHelper: OrientationHelper

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        val out = IntArray(2)

        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }

        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        layoutManager?.let { layoutManager ->
            if (layoutManager.canScrollVertically()) {
                return findStartView(layoutManager, getVerticalHelper(layoutManager))
            } else if (layoutManager.canScrollHorizontally()) {
                return findStartView(layoutManager, getHorizontalHelper(layoutManager))
            }
        }
        return null

    }

    private fun findStartView(
        layoutManager: RecyclerView.LayoutManager?,
        helper: OrientationHelper
    ): View? {

        val childCount = layoutManager?.childCount ?: return null

        var closetChild: View? = null
        val start = helper.startAfterPadding
        var absClosest = Int.Companion.MAX_VALUE

        for (i in 0..<childCount) {
            val child = layoutManager.getChildAt(i)
            val childStart = helper.getDecoratedStart(child)
            val absDistance = Math.abs(childStart - start)

            if (absDistance < absClosest) {
                absClosest = absDistance
                closetChild = child
            }
        }

        return closetChild

    }

    private fun distanceToStart(
        targetView: View,
        helper: OrientationHelper
    ): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager?): OrientationHelper {
        if (!::mHorizontalHelper.isInitialized) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return mHorizontalHelper
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager?): OrientationHelper {
        if (!::mVerticalHelper.isInitialized) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return mVerticalHelper
    }

}