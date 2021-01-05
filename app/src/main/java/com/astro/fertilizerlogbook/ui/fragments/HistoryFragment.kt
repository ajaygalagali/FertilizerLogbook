package com.astro.fertilizerlogbook.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.HistoryAdapter
import com.astro.fertilizerlogbook.adapters.SelectionAdapter
import com.astro.fertilizerlogbook.models.CropModel
import com.astro.fertilizerlogbook.models.HistoryModel
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_selection.*
import kotlinx.android.synthetic.main.popup_add_crop.*


class HistoryFragment : Fragment(R.layout.fragment_history) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var hAdapter : HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val args: HistoryFragmentArgs by navArgs()

        val cropName = args.cropNameArgs

        toolbarHistory.title = cropName

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllHistoryItems(cropName).observe(viewLifecycleOwner,Observer{
            hAdapter.historyList = it
            hAdapter.notifyDataSetChanged()
        })

        hAdapter.setCustomLongClickListener {
            viewModel.deleteHistory(it)
        }



    }




    private fun setupRecyclerView(){
        hAdapter = HistoryAdapter(arrayListOf())

        rvHistoryFragment.apply {
            adapter = hAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}