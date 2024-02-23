package com.example.dataappsql

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

        private val db: NoteDatabaseHelper =NoteDatabaseHelper(context)

    class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val titleText : TextView = itemView.findViewById(R.id.titleText)
        val contentText: TextView = itemView.findViewById(R.id.contentText)
        //update button
        val updateButton:ImageView = itemView.findViewById(R.id.updateButton)
        //delete button
        val  deleteButton:ImageView=itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note =  notes [position]
        holder.titleText.text = note.title
        holder.contentText.text = note.content

        //update button
        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        //delete button
        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note deleted", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newNotes:List<Note>){
         notes = newNotes
        notifyDataSetChanged()
    }
}