package com.xunobulax.rambutan.ui.person

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.xunobulax.rambutan.R
import com.xunobulax.rambutan.data.AppDatabase
import com.xunobulax.rambutan.databinding.FragmentAddPersonBinding


class AddPersonFragment : Fragment() {

    private val viewModel: AddPersonViewModel by navGraphViewModels(R.id.edit_person_graph) {
        AddPersonViewModelFactory(
            AppDatabase.getDatabase(requireContext()).personDao()
        )
    }

    private val args: AddPersonFragmentArgs by navArgs()

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val personId = args.personId

        if (personId > 0L) {
            viewModel.loadPerson(personId)
        }

        viewModel.showBirthdayPickerDialog.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                showBirthdayPicker()
                viewModel.doneNavigating()
            }
        })

        viewModel.navigateToPartnerFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                navController.navigate(AddPersonFragmentDirections.actionAddPersonFragmentToPartnerFragment())
                viewModel.doneNavigating()
            }
        })

        viewModel.navigateToFamilyFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                navController.navigate(AddPersonFragmentDirections.actionAddPersonFragmentToFamilyFragment())
                viewModel.doneNavigating()
            }
        })
    }

    private fun showBirthdayPicker() {
        DatePickerDialog(
            requireContext(),
            R.style.MySpinnerDatePickerStyle,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.setBirthday(year, month.plus(1), dayOfMonth)
            },
            viewModel.getYear(),
            viewModel.getMonth().minus(1),
            viewModel.getDayOfMonth()
        ).show()
    }

}