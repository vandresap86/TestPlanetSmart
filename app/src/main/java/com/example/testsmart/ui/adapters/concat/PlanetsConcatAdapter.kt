package com.example.testsmart.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testsmart.core.BaseConcatHolder
import com.example.testsmart.databinding.PlanetRowBinding
import com.example.testsmart.ui.adapters.PlanetAdapter

class PlanetsConcatAdapter(private val planetsAdapter: PlanetAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PlanetRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(planetsAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(var binding: PlanetRowBinding): BaseConcatHolder<PlanetAdapter>(binding.root){
        override fun bind(adapter: PlanetAdapter) {
            binding.rvPlanets.adapter = adapter
        }
    }


}