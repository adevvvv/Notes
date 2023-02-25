package com.example.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.databinding.FragmentMainNotesBinding

class MainNotesFragment : Fragment() {
    lateinit var binding: FragmentMainNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainNotesBinding.inflate(layoutInflater, container, false)

        binding.flbNewNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate((R.id.action_mainNotesFragment_to_newNotesFragment))
        }

        return binding.root
    }


}