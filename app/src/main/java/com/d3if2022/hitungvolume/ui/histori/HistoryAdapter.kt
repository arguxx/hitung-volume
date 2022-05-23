package com.d3if2022.hitungvolume.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if2022.hitungvolume.R
import com.d3if2022.hitungvolume.databinding.ItemHistoryBinding
import com.d3if2022.hitungvolume.db.Entity
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
    ListAdapter<Entity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Entity>() {
                override fun areItemsTheSame(
                    oldData: Entity, newData: Entity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: Entity, newData: Entity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: Entity) = with(binding) {
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            masukanTextView.text = root.context.getString(R.string.input_x, item.masukan)
            dataTextView.text = root.context.getString(R.string.value_x, item.volume, item.luas)

        }
    }
}
