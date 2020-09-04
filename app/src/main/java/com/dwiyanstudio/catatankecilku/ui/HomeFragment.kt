package com.dwiyanstudio.catatankecilku.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dwiyanstudio.catatankecilku.R
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.db.NoteDatabase
import com.dwiyanstudio.catatankecilku.viewmodel.MainNoteViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private lateinit var mainNoteViewModel: MainNoteViewModel
    private var noteDatabase : NoteDatabase? = null
    private val compositeDisposable = CompositeDisposable()
    private val listNote = ArrayList<DataNote>()
    private val favListNote = ArrayList<DataNote>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        noteDatabase = NoteDatabase.getInstance(view.context)
        getNote()
        getFavNote()
        create_button.setOnClickListener {
            val intent  = Intent(activity,CreateNoteActivity::class.java)
            startActivity(intent)

        }
    }

    private fun getFavNote(){
        compositeDisposable.add(Observable.fromCallable { noteDatabase!!.noteDao().getFavNote(true) }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe{
            favListNote.addAll(it)
            total_notes.text = it.size.toString()

        })
    }

    private fun getNote() {
     compositeDisposable.add(Observable.fromCallable {
         noteDatabase!!.noteDao()
             .getAllNote() }
         .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe{
          listNote.addAll(it)
         total_best.text = it.size.toString()

     })
    }
}