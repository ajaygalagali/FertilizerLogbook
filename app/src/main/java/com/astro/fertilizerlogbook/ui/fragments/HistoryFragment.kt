package com.astro.fertilizerlogbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.HistoryAdapter
import com.astro.fertilizerlogbook.adapters.SelectionAdapter
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_selection.*


class HistoryFragment : Fragment(R.layout.fragment_history) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var hAdapter : HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllHistoryItems().observe(viewLifecycleOwner,Observer{

            hAdapter.differ.submitList(it)
        })

        hAdapter.setCustomLongClickListener {
            viewModel.deleteHistory(it)
        }


    }

    private fun setupRecyclerView(){
        hAdapter = HistoryAdapter()

        rvHistoryFragment.apply {
            adapter = hAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}