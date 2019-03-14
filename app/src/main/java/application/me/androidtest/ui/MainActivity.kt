package application.me.androidtest.ui

import android.os.Bundle
import application.me.androidtest.BaseActivity
import application.me.androidtest.R
import application.me.androidtest.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var adapter: HomePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToolbar()
        setActionTitle(getString(R.string.top_up))

        adapter = HomePagerAdapter(this, supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}