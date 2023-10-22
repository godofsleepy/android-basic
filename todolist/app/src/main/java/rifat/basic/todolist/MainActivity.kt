package rifat.basic.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    var date:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_todo)
        recyclerView.adapter = ListAdapter(this, itemList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dateLayout = findViewById<ConstraintLayout>(R.id.date_layout)
        val txtDate = findViewById<TextView>(R.id.txtdate)
        val txtMonth = findViewById<TextView>(R.id.txtmonth)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        val datetimeNow = Calendar.getInstance().time
        txtDate.text = SimpleDateFormat("dd").format(datetimeNow)
        txtMonth.text = SimpleDateFormat("MMM").format(datetimeNow)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.addOnPositiveButtonClickListener {
            date = it
            txtDate.text = SimpleDateFormat("dd").format(it)
            txtMonth.text = SimpleDateFormat("MMM").format(it)
        }

        dateLayout.setOnClickListener {
            datePicker.show(supportFragmentManager, "datePicker")
        }
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }
    }
}