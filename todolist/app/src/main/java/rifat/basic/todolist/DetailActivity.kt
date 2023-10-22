package rifat.basic.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val extra = intent.getIntExtra("data",0)
        val data = itemList.firstOrNull { it["id"] == extra }
        if (data == null){
            onBackPressedDispatcher.onBackPressed()
        }

        val tfJudul = findViewById<TextInputLayout>(R.id.tf_judul)
        val tfDesc = findViewById<TextInputLayout>(R.id.tf_deskripsi)
        val tfDate = findViewById<TextInputLayout>(R.id.tf_date)
        val button = findViewById<Button>(R.id.elevatedButton)
        val toolbar = findViewById<Toolbar>(R.id.topAppBar)
        toolbar.title = "Detail"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tfJudul.editText?.setText(data!!["title"].toString())
        tfJudul.editText?.isEnabled = false
        tfDesc.editText?.setText(data!!["description"].toString())
        tfDesc.editText?.isEnabled = false
        tfDate.editText?.setText(data!!["date"].toString())
        tfDate.editText?.isEnabled = false
        val complete = data!!["complete"] as Boolean
        if (complete){
            button.visibility = View.GONE
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