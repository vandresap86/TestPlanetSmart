package com.example.testsmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testsmart.core.Resource
import com.example.testsmart.repository.PlanetsRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class PlanetsViewModel(private val repo: PlanetsRepository): ViewModel() {

    fun fetchPlanets() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getPlanets()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class PlanetsViewModelFactory(private val repo: PlanetsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PlanetsRepository::class.java).newInstance(repo)
    }
}