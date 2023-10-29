package rifat.basic.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat


class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val toolbar = findViewById<Toolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val inputDate = findViewById<TextInputEditText>(R.id.input_date)
        val btnSubmit = findViewById<Button>(R.id.elevatedButton)
        val tfJudul = findViewById<TextInputLayout>(R.id.tf_judul)
        val tfDesc = findViewById<TextInputLayout>(R.id.tf_deskripsi)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.addOnPositiveButtonClickListener {
            inputDate.setText( SimpleDateFormat("dd-MM-yyyy").format(it))
        }

        inputDate.setOnClickListener {
            datePicker.show(supportFragmentManager, "datePicker")
        }

        btnSubmit.setOnClickListener {
            if (tfJudul.editText?.text.isNullOrEmpty()){
                tfJudul.error = "Judul wajib diisi"
            }else {
                tfJudul.error = null
            }

            val title = tfJudul.editText?.text.toString()
            val desc = tfDesc.editText?.text.toString()
            val date = inputDate.text.toString()
            val id = System.currentTimeMillis().toInt()

            val data = Todo(
                title,desc, id,date, false
            )
            LocalStorage().saveList(this, data)
            val returnIntent = Intent()
            returnIntent.putExtra("RETURNED_DATA_KEY", true)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}