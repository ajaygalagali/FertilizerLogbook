package com.astro.fertilizerlogbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.models.CropModel
import com.astro.fertilizerlogbook.models.HistoryModel
import kotlinx.android.synthetic.main.crop_row.view.*

class CropAdapter(var cropList : List<CropModel>) :
    RecyclerView.Adapter<CropAdapter.CropViewHolder>() {
    inner class CropViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropViewHolder {
        return CropViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.crop_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CropViewHolder, position: Int) {

        val curItem = cropList[position]

        holder.itemView.apply {
            tvCropName.text = curItem.cropName

            setOnClickListener {
                onItemClickListener?.let { it(curItem) }
            }

            ivDeleteCropName.setOnClickListener {
                onDeleteClickListener?.let { it(curItem) }
            }
        }


    }

    override fun getItemCount(): Int {
        return cropList.size
    }


    private var onItemClickListener:((CropModel)-> Unit)? = null
    private var onDeleteClickListener:((CropModel)-> Unit)? = null


    fun setCustomLongClickListener(listener:(CropModel) -> Unit){
        onItemClickListener = listener
    }

    fun setDeleteListener(listener:((CropModel)->Unit)){
        onDeleteClickListener = listener
    }

}