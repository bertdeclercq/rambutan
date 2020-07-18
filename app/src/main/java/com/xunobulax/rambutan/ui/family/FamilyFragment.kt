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
import com.xunobulax.rambutan.adapters.PersonListener
import com.xunobulax.rambutan.databinding.FragmentFamilyBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FamilyFragment : Fragment() {

    private val viewModel: FamilyViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private lateinit var addPersonFab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFamilyBinding.inflate(inflater, container, false)
        context ?: return binding.root

        addPersonFab = binding.addPersonFab

        val adapter = PersonAdapter(PersonListener { person ->
            navController.navigate(
                FamilyFragmentDirections.actionFamilyFragmentToEditPersonGraph(
                    personId = person.id
                )
            )
        })
        binding.peopleRecyclerview.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addPersonFab.setOnClickListener {
            navController.navigate(FamilyFragmentDirections.actionFamilyFragmentToEditPersonGraph())
        }
    }

    private fun subscribeUi(adapter: PersonAdapter) {
        viewModel.people.observe(
            viewLifecycleOwner,
            Observer { people -> adapter.submitList(people) })
    }

}
