package com.dwiyanstudio.catatankecilku.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dwiyanstudio.catatankecilku.R
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.db.NoteDatabase
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.create_note.*

class CreateNoteActivity : AppCompatActivity(), View.OnClickListener {
    private val compositeDisposable = CompositeDisposable()
    private var noteDatabase : NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)
        save_button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val textJudul = judul_inputtext.text.toString()
        val isiText = isi_catataninputtext.text.toString()
        val noteContent = DataNote(textJudul,isiText,"false")
        inputNote(noteContent,this)
        Snackbar.make(view,"Note Telah Ditambahkan",Snackbar.LENGTH_LONG).show()

    }

    fun inputNote(note: DataNote,context: Context){
        noteDatabase = NoteDatabase.getInstance(context)
        compositeDisposable.add(io.reactivex.rxjava3.core.Observable.fromCallable{noteDatabase!!.noteDao().insertNote(note)}.subscribeOn(
            Schedulers.computation()).subscribe())
    }

}
