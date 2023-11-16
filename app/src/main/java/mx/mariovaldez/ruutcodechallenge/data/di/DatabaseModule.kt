package mx.mariovaldez.ruutcodechallenge.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.mariovaldez.ruutcodechallenge.data.local.FinancialDatabase
import mx.mariovaldez.ruutcodechallenge.data.local.dao.AuthenticationDao
import mx.mariovaldez.ruutcodechallenge.data.local.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): FinancialDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            FinancialDatabase::class.java,
            "financial.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(database: FinancialDatabase): UserDao = database.userDao()

    @Provides
    fun provideAuthenticationDao(database: FinancialDatabase): AuthenticationDao =
        database.authenticationDao()
}
