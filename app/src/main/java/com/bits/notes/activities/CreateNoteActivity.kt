package com.bits.notes.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bits.notes.R
import com.bits.notes.database.NotesDatabase
import com.bits.notes.entities.Note
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.layout_more_slider.*
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {
    private var selectNoteColor: String = "#303030"
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

        initMoreSlider()
        setIndicatorColor()
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
        note.color = selectNoteColor

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

    private fun initMoreSlider(){
        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> = BottomSheetBehavior.from(moreSlider)
        openMore.setOnClickListener {
            if(bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        gray_color.setOnClickListener {
            selectNoteColor = "#303030"
            image_gray_color.setImageResource(R.drawable.ic_baseline_done)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        red_color.setOnClickListener {
            selectNoteColor = "#FF9595"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(R.drawable.ic_baseline_done)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        orange_color.setOnClickListener {
            selectNoteColor = "#FFC895"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(R.drawable.ic_baseline_done)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        yellow_color.setOnClickListener {
            selectNoteColor = "#FFFA95"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(R.drawable.ic_baseline_done)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        green_color.setOnClickListener {
            selectNoteColor = "#B0FF95"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(R.drawable.ic_baseline_done)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        blue_color.setOnClickListener {
            selectNoteColor = "#95DFFF"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(R.drawable.ic_baseline_done)
            image_purple_color.setImageResource(0)
            setIndicatorColor()
        }

        purple_color.setOnClickListener {
            selectNoteColor = "#9584FF"
            image_gray_color.setImageResource(0)
            image_red_color.setImageResource(0)
            image_orange_color.setImageResource(0)
            image_yellow_color.setImageResource(0)
            image_green_color.setImageResource(0)
            image_blue_color.setImageResource(0)
            image_purple_color.setImageResource(R.drawable.ic_baseline_done)
            setIndicatorColor()
        }
    }

    private fun setIndicatorColor(){
        val gradientDrawable = colorIndicator_creteNote.background as GradientDrawable
        gradientDrawable.setColor(Color.parseColor(selectNoteColor))
    }
}