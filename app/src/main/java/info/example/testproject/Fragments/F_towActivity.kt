package info.example.testproject.Fragments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import info.example.testproject.Dataclass.Note
import info.example.testproject.R
import info.example.testproject.adapters.NotedatabaseHelper
import info.example.testproject.databinding.ActivityFtowBinding
import info.example.testproject.databinding.Fragmenttow2Binding

class F_towActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFtowBinding
    private lateinit var db : NotedatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFtowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = NotedatabaseHelper(this)
        binding.savaButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title,content)
            db.insertNote(note)

            Toast.makeText(this, "Note saved.", Toast.LENGTH_SHORT).show()
        }



    }
}