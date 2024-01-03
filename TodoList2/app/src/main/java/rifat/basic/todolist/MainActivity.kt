package rifat.basic.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    val data = mutableListOf<TodoModel>()

    val addTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val todoModel = result.data?.extras?.getParcelable<TodoModel>("todoModel")
                data.add(todoModel!!)

                initRecylerView()
            }
    }

    val detailTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val id = result.data?.extras?.getLong("id")
            val newData =  data.firstOrNull { it.id == id }?.copy(isComplete = true)
            data.removeIf {it.id == id}
            data.add(newData!!)

            initRecylerView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            addTodoLauncher.launch(intent)
        }
        initRecylerView()
    }

    fun initRecylerView(){
        val todoAdapter = TodoAdapter(data,onClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("todoModel", it)
            detailTodoLauncher.launch(intent)
        })

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}