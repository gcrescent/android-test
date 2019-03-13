package application.me.androidtest.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import application.me.androidtest.R
import application.me.androidtest.ui.PulsaFragment

class HomePagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PulsaFragment()
            1 -> PulsaFragment()
            else -> PulsaFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.tab_pulsa)
            1 -> context.getString(R.string.tab_data_package)
            else -> context.getString(R.string.tab_pulsa)
        }
    }
}