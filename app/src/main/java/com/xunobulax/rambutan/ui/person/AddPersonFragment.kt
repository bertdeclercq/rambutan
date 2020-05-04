package com.xunobulax.rambutan.ui.person

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.xunobulax.rambutan.R
import com.xunobulax.rambutan.databinding.FragmentAddPersonBinding
import kotlinx.android.synthetic.main.fragment_add_person.*
import java.time.LocalDate


class AddPersonFragment : Fragment() {

    private val viewModel: AddPersonViewModel by viewModels()
    private lateinit var binding: FragmentAddPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        context ?: binding.root

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.navigateToBirthdayPickerDialog.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                DatePickerDialog(
                    requireContext(),
                    R.style.MySpinnerDatePickerStyle,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        updateBirthday(year, month.plus(1), dayOfMonth)
                    },
                    viewModel.person.birthday?.year ?: LocalDate.now().year,
                    viewModel.person.birthday?.monthValue?.minus(1)
                        ?: LocalDate.now().monthValue.minus(1),
                    viewModel.person.birthday?.dayOfMonth ?: LocalDate.now().dayOfMonth
                ).show()
            }

        })

        viewModel.navigateToFamilyFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToFamilyFragment())
            }
        })
    }

    private fun updateBirthday(year: Int, month: Int, dayOfMonth: Int) {
        viewModel.setBirthday(year, month, dayOfMonth)
        editBirthday.setText(viewModel.person.birthday.toString())
    }

}