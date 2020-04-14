package com.xunobulax.rambutan.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    //TODO Make a FamilyViewModelFactory
    private val viewModel: FamilyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFamilyBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PersonAdapter()
        binding.personList.adapter = adapter
        adapter.submitList(people)
//        subsribeUi(adapter)

        return binding.root
    }

    private fun subsribeUi(adapter: PersonAdapter) {
        viewModel.people.observe(
            viewLifecycleOwner,
            Observer { people -> adapter.submitList(people) })
    }

}
