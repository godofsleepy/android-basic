package rifat.basic.testtodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemTodoAdapter: RecyclerView.Adapter<ItemTodoAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  100
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

}