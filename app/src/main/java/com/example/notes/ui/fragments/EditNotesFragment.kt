package com.example.notes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentEditNotesBinding
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.util.*

class EditNotesFragment : Fragment() {
    lateinit var binding: FragmentEditNotesBinding
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtNotes.setText(oldNotes.data.notes)

        binding.flbSave.setOnClickListener {
            editNotes(it)
        }
        return binding.root
    }

    private fun editNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(
            oldNotes.data.id, title = title, notes = notes, date = notesDate.toString()
        )
        viewModel.updateNotes(data)

        Navigation.findNavController(it!!)
            .navigate((R.id.action_editNotesFragment_to_mainNotesFragment))

    }

}