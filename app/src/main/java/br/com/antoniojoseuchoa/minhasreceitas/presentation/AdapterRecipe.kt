package br.com.antoniojoseuchoa.minhasreceitas.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.antoniojoseuchoa.minhasreceitas.databinding.ItemRecipeBinding
import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain

class AdapterRecipe: ListAdapter<RecipeDomain, AdapterRecipe.ViewHolderRecipe>(DiffCallback()) {

    inner class ViewHolderRecipe(val binding: ItemRecipeBinding): ViewHolder(binding.root){
        fun bind(item: RecipeDomain) = binding.run {
               tvNameRecipe.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecipe {
           val layoutInflater = LayoutInflater.from(parent.context)
           val binding = ItemRecipeBinding.inflate( layoutInflater, parent, false )
           return ViewHolderRecipe(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderRecipe, position: Int) {
          holder.bind(getItem( position ))
    }
}

class DiffCallback(): DiffUtil.ItemCallback<RecipeDomain>(){
    override fun areItemsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) = oldItem == newItem

    override fun areContentsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) = oldItem.id == newItem.id
}