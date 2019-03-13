package application.me.androidtest.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val objects: MutableList<T> = ArrayList()
    var onItemClickListener: OnItemClickListener<T> = object : OnItemClickListener<T> {
        override fun onItemClick(t: T) {

        }
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindItemViewHolder(holder, position, getObject(position))
    }

    fun getObject(position: Int): T {
        return objects[position]
    }

    fun clearObjects() {
        this.objects.clear()
        notifyDataSetChanged()
    }

    fun setObjects(items: List<T>) {
        this.objects.clear()
        addObjects(items)
    }

    fun addObjects(objects: List<T>) {
        this.objects.addAll(objects)
        notifyDataSetChanged()
    }

    abstract fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int, t: T)

    interface OnItemClickListener<T> {
        fun onItemClick(t: T)
    }
}