package rifat.basic.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat

class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val topAppbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val txtTitle = findViewById<TextInputLayout>(R.id.txtTitle)
        val txtDesc = findViewById<TextInputLayout>(R.id.txtDesc)
        val txtDate = findViewById<TextInputLayout>(R.id.txtDate)
        val btnComplete = findViewById<Button>(R.id.btnComplete)

        setSupportActionBar(topAppbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        txtDate.editText?.setOnClickListener {
            datePicker.show(supportFragmentManager, "date")
        }

        datePicker.addOnPositiveButtonClickListener {
            txtDate.editText?.setText(
                SimpleDateFormat("yyyy-MM-dd").format(it)
            )
        }

        btnComplete.setOnClickListener {
            val titleValue = txtTitle.editText?.text
            val descValue = txtDesc.editText?.text
            val dateValue = txtDate.editText?.text

            if (titleValue.isNullOrEmpty()) {
                txtTitle.error = "Judul Wajib diisi"
            } else {
                txtTitle.error = null
            }

            if (descValue.isNullOrEmpty()) {
                txtDesc.error = "Deskripsi Wajib diisi"
            } else {
                txtDesc.error = null
            }

            if (dateValue.isNullOrEmpty()) {
                txtDate.error = "Tanggal Wajib diisi"
            } else {
                txtDate.error = null
            }

            val data = TodoModel(
                title = titleValue.toString(),
                date = dateValue.toString(),
                desc = descValue.toString(),
                isComplete = false,
                id = System.currentTimeMillis(),
            )

            Toast.makeText(this, "Berhasil tambah data", Toast.LENGTH_LONG).show()

            val intentBack = Intent()
            intentBack.putExtra("todoModel", data)
            setResult(RESULT_OK, intentBack)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}