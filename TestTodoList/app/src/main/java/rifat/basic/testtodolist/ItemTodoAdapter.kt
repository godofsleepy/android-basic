package rifat.basic.testtodolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemTodoAdapter(
    val context: Context,
    val list: List<Map<String,Any>>
): RecyclerView.Adapter<ItemTodoAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.tvTitle)
        val txtDesc = view.findViewById<TextView>(R.id.tvDesc)
        val iconCheck = view.findViewById<ImageView>(R.id.iconCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = list[position]["title"].toString()
        holder.txtDesc.text = list[position]["desc"].toString()
        val complete = list[position]["complete"] as Boolean
        if (complete == false) {
            holder.iconCheck.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }

}