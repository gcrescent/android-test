package application.me.androidtest.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val objects: MutableList<T> = ArrayList()

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
}