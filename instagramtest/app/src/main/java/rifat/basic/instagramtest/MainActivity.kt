package rifat.basic.instagramtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recylerView = findViewById<RecyclerView>(R.id.recylerview)
        recylerView.adapter = ItemPostAdapter()
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}