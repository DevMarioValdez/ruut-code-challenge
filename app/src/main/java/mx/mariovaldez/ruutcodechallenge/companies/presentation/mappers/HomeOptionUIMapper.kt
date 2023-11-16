package mx.mariovaldez.ruutcodechallenge.companies.presentation.mappers

import android.content.Context
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.domain.mapper.Mapper
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.HomeOption
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.OptionUI
import mx.mariovaldez.ruutcodechallenge.ktx.findDrawable
import javax.inject.Inject

@Reusable
internal class HomeOptionUIMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<HomeOption, OptionUI> {

    override fun map(value: HomeOption): OptionUI = with(value) {
        OptionUI(
            name,
            context.findDrawable(
                when (value) {
                    HomeOption.ACCOUNT -> R.drawable.icv_account
                }
            ),
            context.getString(
                when (value) {
                    HomeOption.ACCOUNT -> R.string.home_option_account
                }
            ),
        )
    }
}
