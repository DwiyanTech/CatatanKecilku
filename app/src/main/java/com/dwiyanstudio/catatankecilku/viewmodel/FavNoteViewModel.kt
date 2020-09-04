package com.dwiyanstudio.catatankecilku.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiyanstudio.catatankecilku.db.DataNote
import com.dwiyanstudio.catatankecilku.db.NoteDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FavNoteViewModel : ViewModel() {
    private var noteDatabase :NoteDatabase? = null
    private val compositeDisposable = CompositeDisposable()
    private val listNotesLiveData = MutableLiveData<ArrayList<DataNote>>()

    fun getFavNote(context: Context){
        val favListNote = ArrayList<DataNote>()
        noteDatabase = NoteDatabase.getInstance(context)
        compositeDisposable.add(Observable.fromCallable{noteDatabase!!.noteDao().getFavNote(true)}
            .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                favListNote.clear()
                favListNote.addAll(it)
                listNotesLiveData.postValue(favListNote)
            })
    }

    fun getFav() : LiveData<ArrayList<DataNote>> = listNotesLiveData
}