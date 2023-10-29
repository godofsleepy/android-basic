package rifat.basic.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    var date:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_todo)


        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val list = LocalStorage().getList(this)
                val formattedDate =  SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)

                val data =  list.filter { it.date == formattedDate }
                Log.d("Testresult", list.toString())
                recyclerView.adapter = ListAdapter(this, data)
            }
        }
        val dateLayout = findViewById<ConstraintLayout>(R.id.date_layout)
        val txtDate = findViewById<TextView>(R.id.txtdate)
        val txtMonth = findViewById<TextView>(R.id.txtmonth)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val list = LocalStorage().getList(this)
        val datetimeNow = Calendar.getInstance().time
        val formatedNow =  SimpleDateFormat("dd-MM-yyyy").format(datetimeNow)
        val filteredlist =  list.filter { it.date == formatedNow }

        txtDate.text = SimpleDateFormat("dd").format(datetimeNow)
        txtMonth.text = SimpleDateFormat("MMM").format(datetimeNow)

        recyclerView.adapter = ListAdapter(this, filteredlist)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.addOnPositiveButtonClickListener {
            date = it
            val listNew = LocalStorage().getList(this)
            val formattedDate =  SimpleDateFormat("dd-MM-yyyy").format(date)
            val a =  listNew.filter { it.date == formattedDate }
            recyclerView.adapter = ListAdapter(this, a)

            txtDate.text = SimpleDateFormat("dd").format(it)
            txtMonth.text = SimpleDateFormat("MMM").format(it)
        }

        dateLayout.setOnClickListener {
            datePicker.show(supportFragmentManager, "datePicker")
        }
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startForResult.launch(intent, )
        }


    }


}