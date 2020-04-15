package com.xunobulax.rambutan.ui.person

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xunobulax.rambutan.R


class AddPersonFragment : Fragment() {

    companion object {
        fun newInstance() = AddPersonFragment()
    }

    private lateinit var viewModel: AddPersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_person, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddPersonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}