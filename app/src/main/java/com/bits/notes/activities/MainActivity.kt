package com.bits.notes.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bits.notes.R
import com.bits.notes.adapters.NotesAdapter
import com.bits.notes.database.NotesDatabase
import com.bits.notes.entities.Note
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_create_note.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_container_note.view.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var noteList: MutableList<Note>
    lateinit var notesAdapter: NotesAdapter

    private val REQUEST_CODE_ADD_NOTE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAddPost_MainActivity.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE)
        }

        getNotes()

        notesRecyclerView_main.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        noteList = ArrayList()
        notesAdapter = NotesAdapter(noteList)
        notesRecyclerView_main.adapter = notesAdapter
    }

    private fun getNotes(){
        class GetNotesTask: AsyncTask<Void?, Void?, List<Note>>(){
            override fun onPostExecute(result: List<Note>?) {
                super.onPostExecute(result)
                if(noteList.size == 0){
                    if (result != null) {
                        noteList.addAll(result)
                        notesAdapter.notifyDataSetChanged()
                    }

                }else{
                    if (result != null) {
                        noteList.add(0, result[0])
                    }
                    notesAdapter.notifyItemInserted(0)
                    notesRecyclerView_main.smoothScrollToPosition(0)
                }
            }

            override fun doInBackground(vararg params: Void?): List<Note>? {
                return NotesDatabase.getDatabase(applicationContext).noteDao().getAllNotes()
            }
        }

        GetNotesTask().execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_ADD_NOTE && resultCode == Activity.RESULT_OK){
            getNotes()
        }
    }
}