package rifat.basic.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.elevatedButton)
        val tfUsername = findViewById<TextInputLayout>(R.id.textField)
        val tfPassword = findViewById<TextInputLayout>(R.id.password)


        button.setOnClickListener {
            val username = tfUsername.editText?.text.toString()
            if ( username== "rifat" && tfPassword.editText?.text.toString()=="1234"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this,
                    "Username atau Password tidak cocok",
                    Toast.LENGTH_SHORT,
                    ).show()
            }

        }

        val registButton = findViewById<Button>(R.id.btn_register)

        registButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}