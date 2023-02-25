package com.example.notes.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notes.R
import com.example.notes.databinding.FragmentMainNotesBinding
import com.example.notes.model.Notes
import com.example.notes.ui.adapter.NotesAdapter
import com.example.notes.viewmodel.NotesViewModel

class MainNotesFragment : Fragment() {
    lateinit var binding: FragmentMainNotesBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var adapter: NotesAdapter
    var oldMyNotes = arrayListOf<Notes>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = adapter
        }

        binding.flbNewNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate((R.id.action_mainNotesFragment_to_newNotesFragment))
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search notes"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFilteting(p0)
                return true
            }
        })

    }
    private fun notesFilteting(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()

        for (i in oldMyNotes) {
            if (i.title.contains(p0!!)) {
                newFilteredList.add(i)
            }

        }
        adapter.filtering(newFilteredList)
    }


}