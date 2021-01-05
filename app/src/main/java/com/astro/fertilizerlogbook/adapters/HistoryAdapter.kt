package com.astro.fertilizerlogbook.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astro.fertilizerlogbook.R
import com.astro.fertilizerlogbook.models.HistoryModel
import kotlinx.android.synthetic.main.history_row.view.*

class HistoryAdapter(var historyList: List<HistoryModel>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val TAG = "HistoryAdapter"

    /*private val differCallBack = object : DiffUtil.ItemCallback<HistoryModel>(){
        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.hId==newItem.hId
        }

        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem==newItem
        }
    }*/

//    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.history_row,
                parent,
                false
            )
        )
    }

    private var onItemClickListener:((HistoryModel)-> Unit)? = null


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        val curItem = historyList[position]
        holder.setIsRecyclable(false)

        holder.itemView.apply {
            tvTimeStampHistory.text = curItem.timeStamp
            for(item in curItem.items){
                tvNameHistory.append(item.toString()+"\n")
                Log.d(TAG, "onBindViewHolder: $item ")
            }
            ibDelHistory.setOnClickListener {
                onItemClickListener?.let { it(curItem) }
            }
        }



    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    /*override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }*/


    /*@Override
public long getItemId(int position) {
            return position;
}

@Override
public int getItemViewType(int position) {
       return position;
}*/


    fun setCustomLongClickListener(listener:(HistoryModel) -> Unit){
        onItemClickListener = listener
    }
}