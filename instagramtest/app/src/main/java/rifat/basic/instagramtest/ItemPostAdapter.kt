package rifat.basic.instagramtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemPostAdapter: RecyclerView.Adapter<ItemPostAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

}