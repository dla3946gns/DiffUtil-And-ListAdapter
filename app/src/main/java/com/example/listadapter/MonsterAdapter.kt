package com.example.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapter.databinding.ItemProductBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Collections

class MonsterAdapter: ListAdapter<Monster, MonsterAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Monster) {
            with(binding) {
                tvName.text = "Name: ${data.name}"
                tvRace.text = "Race: ${data.race}"
                tvLevel.text = "Level: ${data.level}"
                tvStats.text = "HP: ${data.stats[0]} / MP: ${data.stats[1]} / Exp: ${data.stats[2]}"
                tvEncount.text = "Encounted: ${data.encount}"

                vhLayout.setOnClickListener {
                    Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        fun setAlpha(alpha: Float) {
            with(binding) {
                tvName.alpha = alpha
                tvRace.alpha = alpha
                tvLevel.alpha = alpha
                tvStats.alpha = alpha
                tvEncount.alpha = alpha
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster = getItem(position) as Monster
        holder.bind(monster)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Monster>() {
            override fun areItemsTheSame(oldItem: Monster, newItem: Monster): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Monster, newItem: Monster): Boolean {
                return oldItem == newItem
            }
        }
    }

}