package info.example.testproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.example.testproject.Dataclass.Note
import info.example.testproject.Fragments.fragmenttow
import info.example.testproject.R

class NotesAdapter(private var notes:List<Note>, context: fragmenttow) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView = itemView.findViewById(R.id.titletextview)
        val contentTextView:TextView = itemView.findViewById(R.id.contentTextview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_in_fragmenttow,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

    }
    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}