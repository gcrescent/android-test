package application.me.androidtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import application.me.androidtest.R
import application.me.androidtest.helper.DimensionHelper
import application.me.androidtest.model.Promo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_promo.view.*

class PromoAdapter(private val context: Context) : BaseAdapter<Promo>() {

    // make item width 80% of screen
    var width: Int = DimensionHelper.getScreenWidth(context) * 8 / 10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PromoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_promo, parent, false), width)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int, t: Promo) {
        (holder as PromoViewHolder).onBind(context, t)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(t)
        }
    }

    private class PromoViewHolder(view: View, width: Int) : RecyclerView.ViewHolder(view) {

        init {
            //set width on create to minimalize repetitive process on binding
            view.layoutParams.width = width
        }

        fun onBind(context: Context, promo: Promo) {
            Glide.with(context)
                .load(promo.image)
                .centerCrop()
                .into(itemView.promoImage)
        }
    }
}