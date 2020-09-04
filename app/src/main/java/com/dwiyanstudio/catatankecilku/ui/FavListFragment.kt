package com.dwiyanstudio.catatankecilku.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiyanstudio.catatankecilku.R
import com.dwiyanstudio.catatankecilku.adapter.NoteAdapter
import com.dwiyanstudio.catatankecilku.viewmodel.FavNoteViewModel
import com.dwiyanstudio.catatankecilku.viewmodel.MainNoteViewModel
import kotlinx.android.synthetic.main.fav_note.*

class FavListFragment : Fragment() {

    private lateinit var mainNoteViewModel: FavNoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fav_note,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainNoteViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FavNoteViewModel::class.java)

        mainNoteViewModel.getFavNote(view.context)
        mainNoteViewModel.getFav().observe(viewLifecycleOwner, Observer {
            favRecycler.layoutManager = LinearLayoutManager(view.context)
            val adapter = NoteAdapter(it)
            favRecycler.adapter = adapter
        })
    }
}