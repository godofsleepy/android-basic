package rifat.basic.testtodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.elevatedButton)
        val txtUsername = findViewById<TextInputLayout>(R.id.txtUsername)
        val txtPassword = findViewById<TextInputLayout>(R.id.txtPassword)


        button.setOnClickListener {
            val username = txtUsername.editText?.text.toString()
            val password = txtPassword.editText?.text.toString()

            if (username == "rifat" && password == "1234") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,
                    "Username atau Password tidak cocok",
                    Toast.LENGTH_SHORT,
                ).show()
            }


        }
    }
}