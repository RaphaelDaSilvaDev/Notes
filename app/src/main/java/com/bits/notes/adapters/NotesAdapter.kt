package com.bits.notes.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bits.notes.R
import com.bits.notes.entities.Note
import kotlinx.android.synthetic.main.activity_create_note.*
import java.lang.invoke.ConstantCallSite
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class NotesAdapter(var notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_container_note, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setNote(notes[position])
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var textTitle: TextView = itemView.findViewById(R.id.noteTitle_noteContainer)
        private var textNote: TextView = itemView.findViewById(R.id.note_noteContainer)
        private var textDateTime: TextView = itemView.findViewById(R.id.timeNote_noteContainer)
        private var noteLayout: ConstraintLayout = itemView.findViewById(R.id.noteLayout)

        @SuppressLint("ResourceAsColor")
        @RequiresApi(Build.VERSION_CODES.O)
        fun setNote(note: Note){
            textTitle.text = note.title
            textNote.text = note.noteText

            val newDateFormat = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm a")
            val newDate = LocalDate.parse(note.dateTime, newDateFormat)
            val date = newDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            textDateTime.text = date.toString()

            val gradientDrawable = noteLayout.background as GradientDrawable
            if(note.color.isNotEmpty()){
                gradientDrawable.setColor(Color.parseColor(note.color))
                if(note.color == "#303030"){
                    textTitle.setTextColor(Color.parseColor("#f5f5f5"))
                    textNote.setTextColor(Color.parseColor("#f5f5f5"))
                }else{
                    textTitle.setTextColor(Color.parseColor("#252525"))
                    textNote.setTextColor(Color.parseColor("#252525"))
                }
            }else{
                gradientDrawable.setColor(Color.parseColor("#303030"))
                textTitle.setTextColor(Color.parseColor("#f5f5f5"))
                textNote.setTextColor(Color.parseColor("#f5f5f5"))
            }
        }
    }
}