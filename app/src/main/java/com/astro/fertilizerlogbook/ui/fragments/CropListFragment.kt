package com.astro.fertilizerlogbook.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.adapters.CropAdapter
import com.astro.fertilizerlogbook.models.CropModel
import com.astro.fertilizerlogbook.ui.FertilizerViewModel
import com.astro.fertilizerlogbook.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_crop_list.*
import kotlinx.android.synthetic.main.popup_add_crop.*


class CropListFragment : Fragment(R.layout.fragment_crop_list) {

    lateinit var viewModel: FertilizerViewModel
    lateinit var cAdapter: CropAdapter
    private  val TAG = "CropListFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllCropItems().observe(viewLifecycleOwner, Observer {

            cAdapter.cropList = it
            cAdapter.notifyDataSetChanged()

        })

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.popup_add_crop)


        // OnItem click
        cAdapter.setCustomLongClickListener {
            val action = CropListFragmentDirections.actionCropListFragmentToHistoryFragment(it.cropName!!)
            findNavController().navigate(action)

        }

        // Delete CropName
        cAdapter.setDeleteListener {

            viewModel.deleteCrop(it)

        }

        toolbarCropList.setOnMenuItemClickListener {

            // Adding new crop
            if (it.itemId == R.id.addNewCropMenuItem){

                dialog.show()
                dialog.apply {

                    btnAddCropSave.setOnClickListener {

                        if (etCropName.text.toString().isEmpty()){
                            Toast.makeText(requireContext(),"Enter crop name", Toast.LENGTH_SHORT).show()
                        }else{
                            viewModel.upsertCrop(CropModel(cropName = etCropName.text.toString()))
                            Toast.makeText(requireContext(),"Crop added", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            etCropName.setText("")
                        }

                    }

                }
            }

            return@setOnMenuItemClickListener true

        }


    }


    private fun setupRecyclerView() {
        cAdapter = CropAdapter(arrayListOf())

        rvCropList.apply {
            adapter = cAdapter
            layoutManager = LinearLayoutManager(activity)
        }


    }
}