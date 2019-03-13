package application.me.androidtest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import application.me.androidtest.BaseFragment
import application.me.androidtest.R
import application.me.androidtest.adapter.PromoAdapter
import application.me.androidtest.adapter.PulsaAdapter
import application.me.androidtest.helper.CustomItemDecoration
import application.me.androidtest.model.Promo
import application.me.androidtest.presenter.PulsaFragmentPresenter
import application.me.androidtest.toPx
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_pulsa.*

class PulsaFragment : BaseFragment(), PulsaFragmentPresenter.View {

    private lateinit var presenter: PulsaFragmentPresenter
    private lateinit var pulsaAdapter: PulsaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PulsaFragmentPresenter(this)
        pulsaAdapter = PulsaAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pulsa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productList.layoutManager = LinearLayoutManager(activity)
        productList.addItemDecoration(CustomItemDecoration(8.toPx, CustomItemDecoration.Orientation.HORIZONTAL))
        productList.adapter = pulsaAdapter

        promotionList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        promotionList.isNestedScrollingEnabled = false
        promotionList.hasFixedSize()
        promotionList.addItemDecoration(CustomItemDecoration(16.toPx, CustomItemDecoration.Orientation.HORIZONTAL))
        PagerSnapHelper().attachToRecyclerView(promotionList)

        phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                presenter.checkPhoneNumber(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        presenter.fetchPromotions()
    }

    override fun setOperator(operatorLogo: Int?) {
        Glide.with(requireActivity())
            .load(operatorLogo)
            .apply(RequestOptions.circleCropTransform().centerCrop())
            .into(pulsaOperatorLogo)
    }

    override fun showProducts(products: List<Int>) {
        if (products.isNotEmpty()) {
            productList.visibility = View.VISIBLE
            pulsaAdapter.setObjects(products)
        } else {
            productList.visibility = View.GONE
        }
    }

    override fun hideOperatorAndProducts() {
        productList.visibility = View.GONE
        pulsaAdapter.clearObjects()
    }

    override fun showPromotions(promos: List<Promo>) {
        if (promos.isNotEmpty()) {
            promoContainer.visibility = View.VISIBLE
            val promoAdapter = PromoAdapter(requireActivity())
            promoAdapter.addObjects(promos)
            promotionList.adapter = promoAdapter
        } else {
            promoContainer.visibility = View.GONE
        }
    }
}