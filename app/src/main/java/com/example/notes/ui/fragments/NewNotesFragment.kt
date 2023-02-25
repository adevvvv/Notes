package com.example.notes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.databinding.FragmentNewNotesBinding
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.util.*

class NewNotesFragment : Fragment() {
    lateinit var binding: FragmentNewNotesBinding
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewNotesBinding.inflate(layoutInflater, container, false)

        binding.flbSave.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(
            null, title = title, notes = notes, date = notesDate.toString()
        )
        viewModel.addNotes(data)

        Navigation.findNavController(it!!)
            .navigate((R.id.action_newNotesFragment_to_mainNotesFragment))

    }

}
