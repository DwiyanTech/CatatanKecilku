package com.dwiyanstudio.catatankecilku.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dwiyanstudio.catatankecilku.MainActivity
import com.dwiyanstudio.catatankecilku.R
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.db.NoteDatabase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.create_note.*

class EditNoteActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NOTE = "extra_note"
    }

    private val compositeDisposable = CompositeDisposable()
    private var noteDatabase : NoteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)
        val dataNote : DataNote? = intent.getParcelableExtra(EXTRA_NOTE)
       dataNote?.let {
           judul_inputtext.setText(it.judul)
           isi_catataninputtext.setText(it.isi)

       }
        save_button.setOnClickListener {
            val judulText = judul_inputtext.text.toString()
            val textIsi =   isi_catataninputtext.text.toString()
            val note = DataNote(judulText,textIsi,dataNote!!.isFavourite, dataNote.id)
            inputNote(note)
        }


    }

    fun inputNote(note: DataNote){
        noteDatabase = NoteDatabase.getInstance(this)
        compositeDisposable.add(io.reactivex.rxjava3.core.Observable.fromCallable{noteDatabase!!.noteDao().editNote(note)}.subscribeOn(
            Schedulers.computation()).subscribe())
        val intent  = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}