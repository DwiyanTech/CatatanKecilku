package com.dwiyanstudio.catatankecilku.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.db.NoteDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainNoteViewModel :ViewModel(){

    private var noteDatabase :NoteDatabase? = null
    private val compositeDisposable = CompositeDisposable()
    private val listNotesLiveData = MutableLiveData<ArrayList<DataNote>>()

  fun getNoteData(context: Context) {
        val listNote = ArrayList<DataNote>()
        noteDatabase = NoteDatabase.getInstance(context)
        compositeDisposable.add(Observable.fromCallable { noteDatabase!!.noteDao().getAllNote() }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe{
            listNote.clear()
            listNote.addAll(it)
            listNotesLiveData.postValue(listNote)
        })
    }


    fun getNote() : LiveData<ArrayList<DataNote>> {
        return listNotesLiveData
    }



}