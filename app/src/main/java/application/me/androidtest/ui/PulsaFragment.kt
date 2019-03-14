package application.me.androidtest.ui

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import application.me.androidtest.BaseFragment
import application.me.androidtest.R
import application.me.androidtest.adapter.BaseAdapter
import application.me.androidtest.adapter.ProductAdapter
import application.me.androidtest.adapter.PromoAdapter
import application.me.androidtest.helper.CustomItemDecoration
import application.me.androidtest.model.Product
import application.me.androidtest.model.Promo
import application.me.androidtest.presenter.PulsaFragmentPresenter
import application.me.androidtest.toPx
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_pulsa.*

class PulsaFragment : BaseFragment(), PulsaFragmentPresenter.View {

    companion object {
        private const val DO_PAYMENT = 11
        private const val PICK_CONTACT = 12

        const val TYPE_PULSA = "pulsa"
        const val TYPE_PAKET_DATA = "paketdata"

        private const val INTENT_TYPE = "type"

        fun newInstance(type: String): PulsaFragment {
            val fragment = PulsaFragment()
            val args = Bundle()
            args.putString(INTENT_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: PulsaFragmentPresenter
    private lateinit var type: String

    private lateinit var productAdapter: ProductAdapter
    private var operatorLogo: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PulsaFragmentPresenter(this)
        type = arguments?.getString(INTENT_TYPE) ?: TYPE_PULSA

        productAdapter = ProductAdapter(type)
        productAdapter.onItemClickListener = object : BaseAdapter.OnItemClickListener<Product> {
            override fun onItemClick(t: Product) {
                startActivityForResult(
                    PaymentConfirmationActivity.newIntent(
                        requireContext(),
                        operatorLogo,
                        phoneNumber.text.toString(),
                        t
                    ), DO_PAYMENT
                )
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pulsa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productList.layoutManager = LinearLayoutManager(activity)
        productList.isNestedScrollingEnabled = false
        productList.addItemDecoration(CustomItemDecoration(2.toPx, CustomItemDecoration.Orientation.VERTICAL))
        productList.adapter = productAdapter

        promotionList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        promotionList.isNestedScrollingEnabled = false
        promotionList.hasFixedSize()
        promotionList.addItemDecoration(CustomItemDecoration(16.toPx, CustomItemDecoration.Orientation.HORIZONTAL))
        PagerSnapHelper().attachToRecyclerView(promotionList)

        phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (type == TYPE_PULSA) {
                    presenter.fetchPulsa(p0.toString())
                } else {
                    presenter.fetchPaketData(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        clearPhoneNumber.setOnClickListener {
            phoneNumber.text.clear()
        }

        openContact.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intent, PICK_CONTACT)
        }

        presenter.fetchPromotions()
    }

    override fun setOperator(operatorLogo: Int?) {
        this.operatorLogo = operatorLogo
        operatorLogo?.let {
            Glide.with(requireContext())
                .load(operatorLogo)
                .into(pulsaOperatorLogo)
        } ?: kotlin.run {
            this.operatorLogo = null
            Glide.with(requireContext())
                .load(R.drawable.ic_phone)
                .centerInside()
                .dontAnimate()
                .into(pulsaOperatorLogo)
        }
    }

    override fun showProducts(products: List<Product>) {
        if (products.isNotEmpty()) {
            productList.visibility = View.VISIBLE
            productAdapter.setObjects(products)
        } else {
            productList.visibility = View.GONE
        }
    }

    override fun hideOperatorAndProducts() {
        this.operatorLogo = null
        Glide.with(requireContext())
            .load(R.drawable.ic_phone)
            .centerInside()
            .dontAnimate()
            .into(pulsaOperatorLogo)

        productList.visibility = View.GONE
        productAdapter.clearObjects()
    }

    override fun showPromotions(promos: List<Promo>) {
        if (promos.isNotEmpty()) {
            promoContainer.visibility = View.VISIBLE
            val promoAdapter = PromoAdapter(requireActivity())
            promoAdapter.onItemClickListener = object : BaseAdapter.OnItemClickListener<Promo> {
                override fun onItemClick(t: Promo) {
                    startActivity(PromoDetailActivity.newIntent(requireContext(), t))
                }
            }

            promoAdapter.addObjects(promos)
            promotionList.adapter = promoAdapter
        } else {
            promoContainer.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DO_PAYMENT && resultCode == Activity.RESULT_OK) {
            phoneNumber.text.clear()
        } else if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) run {
            val cursor: Cursor?
            try {
                var phoneNum: String? = null
                // getData() method will have the Content Uri of the selected contact
                val uri = data?.data
                //Query the content uri
                cursor = activity?.contentResolver?.query(uri!!, null, null, null, null)
                cursor?.moveToFirst()
                // column index of the phone number
                val phoneIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                phoneIndex?.let {
                    phoneNum = cursor.getString(it)
                }

                phoneNumber.setText(phoneNum)
                phoneNumber.setSelection(phoneNumber.text.length)
                cursor?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}