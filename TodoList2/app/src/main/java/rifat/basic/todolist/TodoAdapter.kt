package rifat.basic.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(val data: List<TodoModel>, val onClick: (TodoModel) -> Unit): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val imgComplete = view.findViewById<ImageView>(R.id.imgComplete)

        fun gabungin(dataToDo: TodoModel) {
            tvTitle.text = dataToDo.title
            tvDate.text = dataToDo.date

            val isComplete = dataToDo.isComplete
            if (isComplete){
                imgComplete.visibility = View.VISIBLE
            } else {
                imgComplete.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gabungin(data[position])
        holder.itemView.setOnClickListener {
            onClick(data[position])
        }
    }
}