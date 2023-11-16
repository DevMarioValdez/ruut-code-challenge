package mx.mariovaldez.ruutcodechallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalUser

@Dao
interface UserDao {

    @Query("Select * from user where email = :email and password = :password")
    fun observeUser(email: String, password: String): LocalUser?

    @Insert
    suspend fun saveUser(user: LocalUser)
}
