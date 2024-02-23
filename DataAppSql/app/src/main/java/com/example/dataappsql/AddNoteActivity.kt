package com.example.dataappsql

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dataappsql.databinding.ActivityAddNoteBinding


class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteSaved:Boolean = false
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!noteSaved) {
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
        binding=ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= NoteDatabaseHelper(this)
        binding.saveButton.setOnClickListener{
            var title = binding.noteTitle.text.toString()
            var content=binding.noteDesc.text.toString()
            if (title.isEmpty() && content.isEmpty()) {
                showEmptyNoteDialog()
            }
            else {
                val note = Note(0, title, content)
                db.insertNote(note)
                noteSaved = true
                finish()
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Warning")
        builder.setMessage("If you leave, your note will not be saved. Are you sure you want to leave?")
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