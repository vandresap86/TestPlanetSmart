package com.example.testsmart.repository

import androidx.lifecycle.LiveData
import com.example.testsmart.data.local.UserDao
import com.example.testsmart.data.model.UserEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserEntity>> = userDao.readAllData()

    suspend fun addUser(userEntity: UserEntity){
        userDao.saveUser(userEntity)
    }

}