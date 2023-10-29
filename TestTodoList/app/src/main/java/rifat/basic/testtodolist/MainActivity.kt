package rifat.basic.testtodolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    var date: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById<CardView>(R.id.date_picker)
        val floatingButton = findViewById<FloatingActionButton>(R.id.floating_action_button)
        val recylerView = findViewById<RecyclerView>(R.id.recyclerview)
        val txtDate = findViewById<TextView>(R.id.txtDate)
        val txtMonth = findViewById<TextView>(R.id.txtMonth)

        date = Calendar.getInstance().time.time

        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val listFromLocal = LocalStorage().getList(this)
                val data = listFromLocal.filter {
                    it["date"] == SimpleDateFormat("dd-MM-yyyy").format(date)
                }
                recylerView.adapter = ItemTodoAdapter(this, data)
            }
        }


        txtDate.text = SimpleDateFormat("dd").format(date)
        txtMonth.text = SimpleDateFormat("MMM").format(date)

        val listFromLocal = LocalStorage().getList(this)
        val newList = listFromLocal.filter {
            it["date"] == SimpleDateFormat("dd-MM-yyyy").format(date)
        }
        recylerView.adapter = ItemTodoAdapter(this, newList)
        recylerView.layoutManager = LinearLayoutManager(this)


        floatingButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startForResult.launch(intent)
        }

        val datePickerDialog =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePickerDialog.addOnPositiveButtonClickListener {
            date = it
            txtDate.text = SimpleDateFormat("dd").format(date)
            txtMonth.text = SimpleDateFormat("MMM").format(date)

            val newListFromLocal = LocalStorage().getList(this)
            val data = newListFromLocal.filter {
                it["date"] == SimpleDateFormat("dd-MM-yyyy").format(date)
            }
            recylerView.adapter = ItemTodoAdapter(this, data)
        }
        datePicker.setOnClickListener {
            datePickerDialog.show(supportFragmentManager, "datepicker")
        }


    }
}