package com.xunobulax.rambutan.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xunobulax.rambutan.adapters.PersonAdapter
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.databinding.FragmentFamilyBinding
import java.time.LocalDate


class FamilyFragment : Fragment() {

    // This is a test list
    private val people = arrayListOf(
        Person(1, "John", "Doe", "johndoe@email.com", LocalDate.of(2020, 1, 25), 2),
        Person(2, "Jane", "Doe", "janedoe@email.com", LocalDate.of(2000, 1, 25), 1)
    )

    private val viewModel: FamilyViewModel by viewModels()

    private lateinit var addPersonFab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFamilyBinding.inflate(inflater, container, false)
        context ?: return binding.root

        addPersonFab = binding.addPersonFab

        val adapter = PersonAdapter()
        binding.peopleRecyclerview.adapter = adapter
//        adapter.submitList(people)
        subscribeUi(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addPersonFab.setOnClickListener {
            findNavController().navigate(FamilyFragmentDirections.actionFamilyFragmentToAddPersonFragment())
        }
    }

    private fun subscribeUi(adapter: PersonAdapter) {
        viewModel.people.observe(
            viewLifecycleOwner,
            Observer { people -> adapter.submitList(people) })
    }

}
