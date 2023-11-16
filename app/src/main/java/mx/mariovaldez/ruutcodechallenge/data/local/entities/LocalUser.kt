package mx.mariovaldez.ruutcodechallenge.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class LocalUser(
    @PrimaryKey val idUser: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
)
