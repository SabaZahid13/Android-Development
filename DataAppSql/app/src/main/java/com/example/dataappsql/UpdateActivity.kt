package com.example.dataappsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.dataappsql.databinding.ActivityMainBinding
import com.example.dataappsql.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId:Int = -1
    private var noteUpdated:Boolean = false
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!noteUpdated) {
                showConfirmationDialog()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        onBackPressedDispatcher?.addCallback(this, onBackPressedCallback)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        //update logic
        val note = db.getNoteById(noteId)
        binding.editNoteTitle.setText(note.title)
        binding.editNoteContent.setText(note.content)
        binding.editButton.setOnClickListener {
            val newTitle = binding.editNoteTitle.text.toString()
            val newContent = binding.editNoteContent.text.toString()
            if (newTitle.isEmpty() && newContent.isEmpty()) {
                showEmptyNoteDialog()
            } else {
                val updatedNote = Note(noteId, newTitle, newContent)
                db.updateNote(updatedNote)
                finish()
                noteUpdated = true
                Toast.makeText(this, "changes saved", Toast.LENGTH_SHORT).show()

            }

        }
    }
    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Warning")
        builder.setMessage("If you leave, changes will no be saved. Are you sure you want to leave?")
        builder.setPositiveButton("Yes") { dialog, which ->
            finish()
        }
        builder.setNegativeButton("No") { dialog, which ->
            // User clicked the "No" button, do nothing and stay on the current screen
        }
        val dialog = builder.create()
        dialog.show()
    }
        private fun showEmptyNoteDialog() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Empty Note")
            builder.setMessage("Empty note cannot be saved.")
            builder.setPositiveButton("OK") { dialog, which ->
                // Do nothing or handle as needed
            }
            val dialog = builder.create()
            dialog.show()
        }
}