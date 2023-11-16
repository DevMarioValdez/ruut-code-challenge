package mx.mariovaldez.ruutcodechallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Upsert
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalAuthentication
import javax.inject.Inject

@Dao
interface AuthenticationDao {

    @Insert
    fun saveUserAuthentication(localAuthentication: LocalAuthentication)

    @Upsert
    fun updateUserAuthentication(localAuthentication: LocalAuthentication)
}
