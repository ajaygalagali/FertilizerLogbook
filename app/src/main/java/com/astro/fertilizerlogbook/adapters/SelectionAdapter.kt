package com.astro.fertilizerlogbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.models.FertilizerModel
import kotlinx.android.synthetic.main.main_row.view.*

class SelectionAdapter : RecyclerView.Adapter<SelectionAdapter.SelectionViewHolder>() {

    inner class SelectionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<FertilizerModel>(){
        override fun areItemsTheSame(oldItem: FertilizerModel, newItem: FertilizerModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FertilizerModel,
            newItem: FertilizerModel
        ): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder {
        return SelectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row,parent, false))
    }

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {

        var curitem = differ.currentList[position]
        holder.itemView.apply {

            cbMainRow.text = curitem.name

            cbMainRow.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    onItemClickListener?.let { it(curitem) }
                }else{
                    onItemClickListener?.let {
                            it(curitem)
                    }
                }
            }


        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((FertilizerModel)->Unit)? = null

    fun setCbClickListener(listener : ((FertilizerModel)->Unit)){
        onItemClickListener = listener


    }


}