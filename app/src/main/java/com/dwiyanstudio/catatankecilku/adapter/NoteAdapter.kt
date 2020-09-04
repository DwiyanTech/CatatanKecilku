package com.dwiyanstudio.catatankecilku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwiyanstudio.catatankecilku.R
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.ui.EditNoteActivity
import kotlinx.android.synthetic.main.card_listnotes.view.*

class NoteAdapter(private val listNote : ArrayList<DataNote>) : RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.NoteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_listnotes,parent,false)
        return NoteAdapterViewHolder(view)
    }

    class NoteAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataNote: DataNote){
            itemView.judul_catatan.text =  dataNote.judul
            itemView.isi_catatan.text = dataNote.isi
            itemView.setOnClickListener {
                val intentEdit = Intent(it.context,EditNoteActivity::class.java)
                intentEdit.putExtra(EditNoteActivity.EXTRA_NOTE,dataNote)
                it.context.startActivity(intentEdit)
            }
        }

    }

    override fun getItemCount(): Int = listNote.size


    override fun onBindViewHolder(holder: NoteAdapter.NoteAdapterViewHolder, position: Int) {
        holder.bind(listNote[position])

    }
}