package application.me.androidtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import application.me.androidtest.R
import application.me.androidtest.formatMoney
import application.me.androidtest.formatNumber
import application.me.androidtest.model.Product
import kotlinx.android.synthetic.main.item_pulsa.view.*

class ProductAdapter : BaseAdapter<Product>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PulsaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pulsa, parent, false))
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int, t: Product) {
        (holder as PulsaViewHolder).onBind(t)
        holder.itemView.selectAmount.setOnClickListener {
            onItemClickListener.onItemClick(t)
        }
    }

    private class PulsaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(product: Product) {
            itemView.amount.text = product.name
            itemView.selectAmount.text = product.amount.formatMoney
        }
    }
}