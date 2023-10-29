package rifat.basic.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val context: Context,private val itemList: List<Todo>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.txtTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.txtSubtitle)
        val iconCheck =  view.findViewById<ImageView>(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            val id: Int = itemList[position].id
            intent.putExtra("data",id )
            context.startActivity(intent)
        }
        holder.titleTextView.text = itemList[position].title
        holder.descriptionTextView.text = itemList[position].description
        if (itemList[position].complete){
            holder.iconCheck.visibility = View.VISIBLE
        }else {
            holder.iconCheck.visibility = View.GONE
        }
    }
}