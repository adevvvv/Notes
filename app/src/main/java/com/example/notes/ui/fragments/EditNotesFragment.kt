package com.example.notes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentEditNotesBinding
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {
    lateinit var binding: FragmentEditNotesBinding
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
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

    private fun deleteNote() {
        val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.dialog_delete)
        val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
        val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

        textViewYes?.setOnClickListener {
            viewModel.deleteNotes(oldNotes.data.id!!)
            view?.findNavController()?.navigate(
                R.id.action_editNotesFragment_to_mainNotesFragment
            )
            bottomSheet.dismiss()
        }
        textViewNo?.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }

        return true
    }

}