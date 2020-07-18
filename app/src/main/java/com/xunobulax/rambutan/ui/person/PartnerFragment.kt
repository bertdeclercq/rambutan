package com.xunobulax.rambutan.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.setupWithNavController
import com.xunobulax.rambutan.R
import com.xunobulax.rambutan.adapters.PersonAdapter
import com.xunobulax.rambutan.adapters.PersonListener
import com.xunobulax.rambutan.databinding.FragmentPartnerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_partner.view.*


@AndroidEntryPoint
class PartnerFragment : Fragment() {

    private val viewModel: EditPersonViewModel by navGraphViewModels(R.id.edit_person_graph) {
        defaultViewModelProviderFactory
    }

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPartnerBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PersonAdapter(PersonListener { partner ->
            viewModel.onPartnerSet(partner)
            navController.navigate(PartnerFragmentDirections.actionPartnerFragmentToEditPersonFragment())
        })
        binding.lifecycleOwner = this
        binding.peopleRecyclerview.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.toolbar.setupWithNavController(navController)
    }

    private fun subscribeUi(adapter: PersonAdapter) {
        viewModel.partnersList.observe(viewLifecycleOwner, Observer { people ->
            adapter.submitList(people)
        })
    }
}