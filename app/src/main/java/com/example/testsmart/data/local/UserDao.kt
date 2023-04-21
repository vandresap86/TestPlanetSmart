package com.example.testsmart.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testsmart.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUser(user: UserEntity)


    @Query("SELECT * FROM user_table ORDER BY id ASC" )
    fun readAllData(): LiveData<List<UserEntity>>


}