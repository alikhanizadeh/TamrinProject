package info.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import info.example.roomdatabase.UserDao
import info.example.roomdatabase.model.User


class UserRepository(private val userDao: UserDao)
{
    val reedAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User)
    {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }


}
