package com.dwiyanstudio.catatankecilku.ui

import android.content.Intent
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
import com.dwiyanstudio.catatankecilku.viewmodel.MainNoteViewModel
import kotlinx.android.synthetic.main.list_notes.*

class NoteListFragment : Fragment() {

    private lateinit var mainNoteViewModel: MainNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_notes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainNoteViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainNoteViewModel::class.java)
        mainNoteViewModel.getNoteData(view.context)
        mainNoteViewModel.getNote().observe(viewLifecycleOwner, Observer {
                val adapterNote = NoteAdapter(it)
                noteRecycler.layoutManager = LinearLayoutManager(this.context)
                noteRecycler.adapter = adapterNote

            create_button.setOnClickListener {
                val intent  = Intent(activity,CreateNoteActivity::class.java)
                startActivity(intent)
            }

        })
    }

}