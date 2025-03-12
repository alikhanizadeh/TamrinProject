package info.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import info.example.roomdatabase.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)


    @Update
    suspend fun updateUser(user: User)


    @Delete
    suspend fun deleteUser(user: User)


    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()




}


//@Delete
//suspend fun deleteTaskItem(taskItem: TaskItem)