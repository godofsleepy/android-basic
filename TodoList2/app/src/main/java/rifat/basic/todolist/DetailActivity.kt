package rifat.basic.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val topAppbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val txtTitle = findViewById<TextInputLayout>(R.id.txtTitle)
        val txtDesc = findViewById<TextInputLayout>(R.id.txtDesc)
        val txtDate = findViewById<TextInputLayout>(R.id.txtDate)
        val btnComplete = findViewById<Button>(R.id.btnComplete)

        topAppbar.title = "Detail Todo"
        btnComplete.text = "Selesai"

        val todoModel = intent.extras?.getParcelable<TodoModel>("todoModel")

        txtTitle.editText?.setText(todoModel!!.title)
        txtDate.editText?.setText(todoModel!!.date)
        txtDesc.editText?.setText(todoModel!!.desc)

        txtTitle.isEnabled = false
        txtDesc.isEnabled = false
        txtDate.isEnabled = false

        if (todoModel!!.isComplete){
            btnComplete.visibility = View.GONE
        }

        btnComplete.setOnClickListener {
            val intentBack = Intent()
            intentBack.putExtra("id", todoModel.id)
            setResult(RESULT_OK, intentBack)
            finish()
        }

        setSupportActionBar(topAppbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}