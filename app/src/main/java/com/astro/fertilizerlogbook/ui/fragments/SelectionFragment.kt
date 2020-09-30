package com.astro.fertilizerlogbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.SelectionAdapter
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var sAdapter: SelectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllFertilizers().observe(viewLifecycleOwner,Observer{
            sAdapter.differ.submitList(it)
        })


    }

    private fun setupRecyclerView(){
        sAdapter = SelectionAdapter()

        rvSelectionFragment.apply {
            adapter = sAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}