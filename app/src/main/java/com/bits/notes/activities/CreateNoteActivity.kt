package com.bits.notes.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bits.notes.R
import com.bits.notes.database.NotesDatabase
import com.bits.notes.entities.Note
import kotlinx.android.synthetic.main.activity_create_note.*
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
         backToHome_addPost.setOnClickListener {
            finish()
        }

        savePost_addPost.setOnClickListener {
            saveNote()
        }

        timeNote_createNote.text = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(
            Date()
        )
    }

    private fun saveNote(){
        if(noteTitle_createNote.text.isNullOrEmpty()){
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_SHORT).show()
            return
        }else if (note_createNote.text.isNullOrEmpty()){
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note()
        note.title = noteTitle_createNote.text.toString()
        note.noteText = note_createNote.text.toString()
        note.dateTime = timeNote_createNote.text.toString()

        class SaveNoteTask: AsyncTask<Void?, Void?, Void?>() {
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            override fun doInBackground(vararg params: Void?): Void? {
                NotesDatabase.getDatabase(applicationContext).noteDao().insertNote(note)
                return null
            }
        }

        SaveNoteTask().execute()

    }
}