package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener:INotesRVAdapter):RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {
    val allNotes=ArrayList<Notes>()
    inner class NotesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       val viewHolder=NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false))
       viewHolder.deleteButton.setOnClickListener{
           listener.onItemClicked(allNotes[viewHolder.adapterPosition])
       }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes=allNotes[position]
        holder.textView.text=currentNotes.text

    }

    override fun getItemCount(): Int {
        return allNotes.size

    }
    fun updateList(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun onItemClicked(notes: Notes)
}