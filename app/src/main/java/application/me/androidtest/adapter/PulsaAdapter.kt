package application.me.androidtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import application.me.androidtest.R
import application.me.androidtest.formatMoney
import application.me.androidtest.formatNumber
import kotlinx.android.synthetic.main.item_pulsa.view.*

class PulsaAdapter : BaseAdapter<Int>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PulsaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pulsa, parent, false))
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int, t: Int) {
        (holder as PulsaViewHolder).onBind(t)
        holder.itemView.selectAmount.setOnClickListener {
            onItemClickListener.onItemClick(t)
        }
    }

    private class PulsaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(amount: Int) {
            itemView.amount.text = amount.formatNumber
            itemView.selectAmount.text = amount.formatMoney
        }
    }
}