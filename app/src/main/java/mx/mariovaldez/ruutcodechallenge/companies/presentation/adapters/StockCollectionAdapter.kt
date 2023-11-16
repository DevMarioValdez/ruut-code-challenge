package mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.companies.domain.usecases.GetStockCollections
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockCollection
import mx.mariovaldez.ruutcodechallenge.ktx.gone
import javax.inject.Inject

internal class StockCollectionAdapter @Inject constructor(
    context: Context,
) : ArrayAdapter<StockCollection>(context, 0, GetStockCollections().invoke()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent, true)
    }

    private fun initView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
        flag: Boolean
    ): View {

        val stockCollection = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_stock_collection, parent, false)

        val stockImage = view.findViewById<ImageView>(R.id.stock_collection_image_view)
        val stockName = view.findViewById<TextView>(R.id.stock_collection_name_text_view)
        val arrowDown = view.findViewById<ImageView>(R.id.arrow_down_image_view)

        stockName.text = stockCollection?.value
        stockCollection?.drawable?.let { stockImage.setBackgroundResource(it) }
        if (flag) arrowDown.gone()

        return view
    }
}
