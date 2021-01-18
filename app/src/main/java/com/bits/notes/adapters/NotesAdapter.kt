package com.bits.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bits.notes.R
import com.bits.notes.entities.Note


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

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setNote(notes[position])
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var textTitle: TextView = itemView.findViewById(R.id.noteTitle_noteContainer)
        private var textNote: TextView = itemView.findViewById(R.id.note_noteContainer)
        private var textDateTime: TextView = itemView.findViewById(R.id.timeNote_noteContainer)

        fun setNote(note: Note){
            textTitle.text = note.title
            textNote.text = note.noteText
            textDateTime.text = note.dateTime
        }
    }
}