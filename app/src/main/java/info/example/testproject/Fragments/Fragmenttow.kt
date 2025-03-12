package info.example.testproject.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import info.example.testproject.adapters.NotedatabaseHelper
import info.example.testproject.adapters.NotesAdapter
import info.example.testproject.databinding.FragmenttowBinding

class fragmenttow : Fragment(){
    lateinit var binding: FragmenttowBinding
    private lateinit var db : NotedatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmenttowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotedatabaseHelper(requireContext())
        notesAdapter = NotesAdapter(db.getAllNotes(),this )
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(context, F_towActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())

    }
}