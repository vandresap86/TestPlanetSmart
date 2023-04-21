package com.example.testsmart.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testsmart.core.BaseViewHolder
import com.example.testsmart.data.model.Planet
import com.example.testsmart.databinding.PlanetItemBinding

class PlanetAdapter(private val planetsList: List<Planet>,
                    private val itemClickListener: OnPlanetClickListener
                    ): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPlanetClickListener{
        fun onPlanetClick(planet: Planet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = PlanetsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onPlanetClick(planetsList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PlanetsViewHolder -> holder.bind(planetsList[position])
        }
    }

    override fun getItemCount(): Int = planetsList.size

    private inner class PlanetsViewHolder(
        val binding: PlanetItemBinding,
        val context: Context
    ) : BaseViewHolder<Planet>(binding.root) {
        override fun bind(item: Planet) {
            //Glide.with(context).load("${item.name}").centerCrop().into(binding.imgPlanet)
            binding.txtNamePlanet.text = item.name
            binding.txtClimatePlanet.text = item.climate
            binding.txtDiameterPlanet.text = item.diameter
            binding.txtUrlPlanet.text = item.url
        }
    }
}