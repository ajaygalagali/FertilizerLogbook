package com.astro.fertilizerlogbook.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.db.FertilizerDatabase
import com.astro.fertilizerlogbook.db.repository.FertilizerRepository
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_add_new_item.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: FertilizerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        val repository = FertilizerRepository(FertilizerDatabase(this))
        val viewModelFactory = FertilizerViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(FertilizerViewModel::class.java)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_add_new_item)

        // Adding New Item to catalog
        toolbarMain.setOnMenuItemClickListener {

            when(it.title){
                "Add new item" -> {

                    dialog.show()
                    dialog.apply {
                        btnAdd.setOnClickListener {
                            if (etNamePopup.text.toString().isNotEmpty()) {
                                viewModel.upsertCatalog(FertilizerModel(name = etNamePopup.text.toString()))
                                dismiss()
                            } else {

                            }

                        }
                    }

                    return@setOnMenuItemClickListener true
                }

                else -> return@setOnMenuItemClickListener false
            }

        }



    }
}