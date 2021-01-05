package com.astro.fertilizerlogbook.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.db.FertilizerDatabase
import com.astro.fertilizerlogbook.repository.FertilizerRepository
import com.astro.fertilizerlogbook.models.FertilizerModel
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




    }
}