package com.example.testsmart.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsmart.R
import com.example.testsmart.core.Resource
import com.example.testsmart.data.model.Planet
import com.example.testsmart.data.remote.RemotePlanetDataSource
import com.example.testsmart.databinding.FragmentCatalogueBinding
import com.example.testsmart.presentation.PlanetsViewModel
import com.example.testsmart.presentation.PlanetsViewModelFactory
import com.example.testsmart.presentation.UserViewModel
import com.example.testsmart.repository.PlanetsRepositoryImpl
import com.example.testsmart.repository.RetrofitClient
import com.example.testsmart.ui.adapters.PlanetAdapter
import com.example.testsmart.ui.adapters.concat.PlanetsConcatAdapter

class CatalogueFragment : Fragment(R.layout.fragment_catalogue), PlanetAdapter.OnPlanetClickListener {

    private lateinit var binding: FragmentCatalogueBinding
    private val viewModel by viewModels<PlanetsViewModel>{
        PlanetsViewModelFactory(
            PlanetsRepositoryImpl(
                RemotePlanetDataSource(RetrofitClient.webService)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCatalogueBinding.bind(view)

        var concatAdapter = ConcatAdapter()

        viewModel.fetchPlanets().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, PlanetsConcatAdapter(PlanetAdapter(result.data.results, this@CatalogueFragment)))
                    }
                    binding.rvPlanets.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

    override fun onPlanetClick(planet: Planet) {
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToDetailFragment(planet.residents.toString(),planet.name,planet.climate
            ,planet.rotation_period,planet.terrain)
        findNavController().navigate(action)
    }
}