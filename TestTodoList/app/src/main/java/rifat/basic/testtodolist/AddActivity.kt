package rifat.basic.testtodolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val txtTitle = findViewById<TextInputLayout>(R.id.txtTitle)
        val txtDesc = findViewById<TextInputLayout>(R.id.txtDesc)
        val txtDate = findViewById<TextInputLayout>(R.id.txtDate)
        val button = findViewById<Button>(R.id.elevatedButton)

        toolbar.title = "Add"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val datePickerDialog =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePickerDialog.addOnPositiveButtonClickListener {
            txtDate.editText?.setText(SimpleDateFormat("dd-MM-yyyy").format(it))
        }

        txtDate.editText?.setOnClickListener {
            datePickerDialog.show(supportFragmentManager, "datepicker")
        }

        button.setOnClickListener {
            val title = txtTitle.editText?.text
            val desc = txtDesc.editText?.text
            val date = txtDate.editText?.text

            if (title.isNullOrEmpty()){
                txtTitle.error = "Field ini wajib diisi"
            }else {
                txtTitle.error = null
            }

            if (desc.isNullOrEmpty()){
                txtDesc.error = "Field ini wajib diisi"
            }else {
                txtDesc.error = null
            }

            if (date.isNullOrEmpty()){
                txtDate.error = "Field ini wajib diisi"
            }else {
                txtDate.error = null
            }


            val newData =  mapOf<String,Any>(
                "id" to System.currentTimeMillis().toInt(),
                "title" to title.toString(),
                "desc" to desc.toString(),
                "date" to date.toString(),
                "complete" to false,
            )

            LocalStorage().saveList(this, newData)
            val intentBack = Intent()
            setResult(Activity.RESULT_OK, intentBack)
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}