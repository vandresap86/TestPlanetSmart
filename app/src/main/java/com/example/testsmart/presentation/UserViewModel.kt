package com.example.testsmart.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.testsmart.data.local.UserDatabase
import com.example.testsmart.data.model.UserEntity
import com.example.testsmart.repository.PlanetsRepository
import com.example.testsmart.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<UserEntity>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository =UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(userEntity: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userEntity)
        }
    }

}

