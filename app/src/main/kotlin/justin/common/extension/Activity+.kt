package justin.common.extension

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View

/**
 * @author justin on 2017/03/31 11:12
 * @version V1.0
 */

fun Activity.makeShareViewOptions(vararg shares: View): Bundle {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val scenes = arrayOfNulls<Pair<View, String>>(shares.size)
        for ((index, v) in shares.withIndex()) {
            val transitionName = v.transitionName
            val pair = Pair(v, transitionName!!)
            scenes[index] = pair
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(this, *scenes).toBundle()
    } else {
        return Bundle()
    }
}


