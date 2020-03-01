// To access extension function from Java class.
@file:JvmName("Utility")

package navanth.com.herokuapp.Utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import navanth.com.herokuapp.R

/**
 * This is extension function is to launch activity.
 */
fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    Intent(this, it).apply {
        putExtras(Bundle().apply(extras))
        startActivity(this)
    }
}

fun Context.checkInternetConnection(): Boolean {
    val connectivity = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivity == null) {
        return false
    } else {
        val info = connectivity.allNetworkInfo
        if (info != null) {
            for (anInfo in info) {
                if (anInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
    }
    return false
}

fun Context.showSnackBar(
    view: RelativeLayout,
    message: String,
    messageColor: Int
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .apply {
            (view.findViewById<View>(R.id.snackbar_text) as? TextView)?.setTextColor(messageColor)
            show()
        }
}