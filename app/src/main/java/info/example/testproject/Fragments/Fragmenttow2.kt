package info.example.testproject.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import info.example.testproject.Dataclass.Note
import info.example.testproject.adapters.NotedatabaseHelper
import info.example.testproject.R
import info.example.testproject.databinding.Fragmenttow2Binding

class fragmenttow2:Fragment(){
    lateinit var binding: Fragmenttow2Binding
    private lateinit var db : NotedatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragmenttow2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = NotedatabaseHelper(requireContext())
        binding.savaButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title,content)
            db.insertNote(note)

            Toast.makeText(context, "Note saved.", Toast.LENGTH_SHORT).show()
        }

    }
}