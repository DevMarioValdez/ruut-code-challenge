package mx.mariovaldez.ruutcodechallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.mariovaldez.ruutcodechallenge.data.local.dao.AuthenticationDao
import mx.mariovaldez.ruutcodechallenge.data.local.dao.UserDao
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalAuthentication
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalUser

@Database(
    entities = [
        LocalUser::class,
        LocalAuthentication::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FinancialDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun authenticationDao(): AuthenticationDao
}
