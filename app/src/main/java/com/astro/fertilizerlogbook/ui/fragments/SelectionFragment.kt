package com.astro.fertilizerlogbook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.SelectionAdapter
import com.astro.fertilizerlogbook.models.HistoryModel
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_selection.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var sAdapter: SelectionAdapter
    lateinit var selectedList: MutableList<String>

    private val TAG = "SelectionFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        selectedList = mutableListOf()

        viewModel.getAllFertilizers().observe(viewLifecycleOwner,Observer{
            sAdapter.differ.submitList(it)
        })

        sAdapter.setCbClickListener {
            if (selectedList.contains(it.name.toString())){
                selectedList.remove(it.name.toString())
                Log.d(TAG, "onViewCreated: Removed - ${it.name.toString()}")
            }else {
                selectedList.add(it.name.toString())
                Log.d(TAG, "onViewCreated: Added - ${it.name.toString()}")

            }
        }

        // FAB click handling
        fab_select.setOnClickListener {
            var localeDateTime = LocalDateTime.now()
            var dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            var timeStamp = localeDateTime.format(dateFormatter)

            val clonelist = mutableListOf<String>()
            clonelist.addAll(selectedList)

            viewModel.upsertHistory(HistoryModel(items = clonelist,timeStamp = timeStamp))
            selectedList.clear()


            Snackbar.make(requireActivity().findViewById(R.id.frameLayoutMain),"Added Successfully",Snackbar.LENGTH_SHORT).apply {
                setAction("Dismiss"){
                    this.dismiss()
                }
            }.show()
        }


    }

    private fun setupRecyclerView(){
        sAdapter = SelectionAdapter()

        rvSelectionFragment.apply {
            adapter = sAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}