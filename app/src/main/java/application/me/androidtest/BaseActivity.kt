package application.me.androidtest

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar)?.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    fun showToolbar() {
        setupToolbar()
        if (supportActionBar != null) {
            val view = layoutInflater.inflate(R.layout.custom_toolbar, null)
            val layout = Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT)
            supportActionBar?.setCustomView(view, layout)
            supportActionBar?.setDisplayShowCustomEnabled(true)
            supportActionBar?.show()
        }
    }

    fun setActionTitle(title: String) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.findViewById<TextView>(R.id.toolbarTitle)?.text = title
        }
    }

    fun showBackButton() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (null != toolbar) {
            val button = toolbar.findViewById<ImageButton>(R.id.toolbarLeft)
            if (button != null) {
                button.visibility = View.VISIBLE
                button.setImageResource(R.drawable.ic_arrow_back)
                button.setOnClickListener { onBackPressed() }
            }
        }
    }

    fun showCloseButton() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (null != toolbar) {
            val button = toolbar.findViewById<ImageButton>(R.id.toolbarLeft)
            if (button != null) {
                button.visibility = View.VISIBLE
                button.setImageResource(R.drawable.ic_close)
                button.setOnClickListener { onBackPressed() }
            }
        }
    }
}