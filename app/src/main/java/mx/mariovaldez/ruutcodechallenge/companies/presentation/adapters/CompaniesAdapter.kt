package mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.databinding.ItemCollectionBinding
import mx.mariovaldez.ruutcodechallenge.ktx.context
import mx.mariovaldez.ruutcodechallenge.ktx.createImageUrl
import mx.mariovaldez.ruutcodechallenge.ktx.findColorInt
import mx.mariovaldez.ruutcodechallenge.ktx.formatMoneyCurrency
import mx.mariovaldez.ruutcodechallenge.ktx.formatToPercent

internal class CompaniesAdapter(
    private val listener: (StockUI) -> Unit
) : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    private val collections: MutableList<StockUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCollectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = collections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit =
        holder.bind(collections[position])

    @SuppressLint("NotifyDataSetChanged")
    fun addCollections(collections: List<StockUI>) {
        this.collections.clear()
        this.collections.addAll(collections)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stockUI: StockUI) = with(binding) {
            with(imageView) {
                load(createImageUrl(stockUI.symbol)){
                    transformations(CircleCropTransformation())
                    crossfade(true)
                    crossfade(500)
                }
            }
            symbolNameTextView.text = stockUI.symbol
            companyNameTextView.text = stockUI.companyName
            latestPriceTextView.text = stockUI.latestPrice.formatMoneyCurrency()
            changePercentTextView.text = stockUI.changePercent.formatToPercent()
            val color = context.findColorInt(
                if (stockUI.changePercent <= 0) R.color.red_light else R.color.green_light
            )
            changePercentTextView.setTextColor(color)
            root.setOnClickListener {
                listener(stockUI)
            }
        }
    }
}
