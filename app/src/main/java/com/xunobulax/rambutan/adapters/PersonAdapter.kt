package com.xunobulax.rambutan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.databinding.ListItemPersonBinding


class PersonAdapter : ListAdapter<Person, RecyclerView.ViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        PersonViewHolder(
            ListItemPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = getItem(position)
        (holder as PersonViewHolder).bind(person)
    }


    class PersonViewHolder(private val binding: ListItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Person) {
            binding.apply {
                person = item
                executePendingBindings()
            }
        }
    }
}

private class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem

}
