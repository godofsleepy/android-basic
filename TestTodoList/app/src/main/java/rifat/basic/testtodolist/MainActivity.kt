package rifat.basic.testtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recylerView = findViewById<RecyclerView>(R.id.recyclerview)
        recylerView.adapter = ItemTodoAdapter()
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}