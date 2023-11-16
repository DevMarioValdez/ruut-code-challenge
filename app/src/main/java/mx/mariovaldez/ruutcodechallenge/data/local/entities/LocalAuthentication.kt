package mx.mariovaldez.ruutcodechallenge.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "authentication"
)
data class LocalAuthentication(
    @PrimaryKey val idUser: String,
    val token: String,
    val expiresIn: String,
)
