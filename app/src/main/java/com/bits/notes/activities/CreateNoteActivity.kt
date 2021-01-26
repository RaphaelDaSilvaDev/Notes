package com.bits.notes.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.TaskInfo
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bits.notes.R
import com.bits.notes.database.NotesDatabase
import com.bits.notes.entities.Note
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.layout_more_slider.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class CreateNoteActivity : AppCompatActivity() {
    private var selectNoteColor: String = "#303030"
    private val REQUEST_CODE_STORAGE: Int = 1
    private val REQUEST_CODE_SELECT_IMAGE: Int = 2

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

        layoutImage.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            if(ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_STORAGE)
            }else{
                selectImage()
            }
        }
    }

    private fun setIndicatorColor(){
        val gradientDrawable = colorIndicator_creteNote.background as GradientDrawable
        gradientDrawable.setColor(Color.parseColor(selectNoteColor))
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun selectImage(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if(intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_STORAGE && grantResults.size > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage()
            }else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK){
            if(data != null){
                val selectedImageUri: Uri? = data.data
                if(selectedImageUri != null){
                    try {
                        val inputStream = contentResolver.openInputStream(selectedImageUri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        imageNote.setImageBitmap(bitmap)
                    }catch (exception: Exception){
                        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}