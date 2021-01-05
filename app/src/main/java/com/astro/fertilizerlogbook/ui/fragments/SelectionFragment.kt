package com.astro.fertilizerlogbook.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.SelectionAdapter
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.models.HistoryModel
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_selection.*
import kotlinx.android.synthetic.main.popup_add_new_item.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var sAdapter: SelectionAdapter
    lateinit var selectedList: MutableList<String>
    lateinit var cropList : MutableList<String>
    var selectedCrop = ""
    private val TAG = "SelectionFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        selectedList = mutableListOf()

        cropList = mutableListOf()



        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,cropList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Adding cropList to Spinner
        viewModel.getAllCropItems().observe(viewLifecycleOwner, Observer {

            for (crop in it) {
                Log.d(TAG, "onViewCreated: ${crop.cropName}")
                cropList.add(crop.cropName!!)
            }
            spinnerAdapter.notifyDataSetChanged()
        })

        spinnerCropSelection.adapter = spinnerAdapter

        // Spinner Selection
        spinnerCropSelection.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCrop = cropList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

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
            val localeDateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val timeStamp = localeDateTime.format(dateFormatter)

            val clonelist = mutableListOf<String>()
            clonelist.addAll(selectedList)

            viewModel.upsertHistory(HistoryModel(items = clonelist,timeStamp = timeStamp,crop = selectedCrop))
            selectedList.clear()


            Snackbar.make(requireActivity().findViewById(R.id.frameLayoutMain),"Added Successfully",Snackbar.LENGTH_SHORT).apply {
                setAction("Dismiss"){
                    this.dismiss()
                }
            }.show()
        }

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.popup_add_new_item)

        // Adding New Item to catalog
        toolbarHome.setOnMenuItemClickListener {

            if (it.itemId == R.id.addNewItemMenu){
                dialog.show()
                dialog.apply {
                    btnAdd.setOnClickListener {
                        if (etNamePopup.text.toString().isNotEmpty()) {
                            viewModel.upsertCatalog(FertilizerModel(name = etNamePopup.text.toString().trim()))
                            Toast.makeText(requireContext(),"Saved",Toast.LENGTH_SHORT).show()
                            dismiss()
                        }else{
                            Toast.makeText(requireContext(),"Enter Fertilizer name",Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }

            return@setOnMenuItemClickListener true

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