package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation

import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.databinding.FragmentStockDetailBinding
import mx.mariovaldez.ruutcodechallenge.ktx.createImageUrl
import mx.mariovaldez.ruutcodechallenge.ktx.exhaustive
import mx.mariovaldez.ruutcodechallenge.ktx.findColorInt
import mx.mariovaldez.ruutcodechallenge.ktx.findDrawable
import mx.mariovaldez.ruutcodechallenge.ktx.formatMoneyCurrency
import mx.mariovaldez.ruutcodechallenge.ktx.formatToPercent
import mx.mariovaldez.ruutcodechallenge.ktx.observe
import mx.mariovaldez.ruutcodechallenge.ktx.viewBinding
import mx.mariovaldez.ruutcodechallenge.ktx.visible
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.TradeUI

@AndroidEntryPoint
class StockDetailFragment : Fragment(R.layout.fragment_stock_detail) {

    private val binding: FragmentStockDetailBinding by viewBinding(
        FragmentStockDetailBinding::bind
    )
    private val viewModel: StockDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        viewModel.fetchData()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: StockDetailViewModel.State) {
        when (state) {
            StockDetailViewModel.State.Default -> {}
            StockDetailViewModel.State.Error -> {}
            StockDetailViewModel.State.Loading -> {}
            is StockDetailViewModel.State.Success -> setData(state.tradeUI.trades)
        }.exhaustive
    }

    private fun setupViews() {
        setupListeners()
        with(binding) {
            stockNameTextView.text = viewModel.getCompanyName()
            priceTextView.text = viewModel.getPrice()?.formatMoneyCurrency()
            symbolNameTextView.text = viewModel.getSymbol()
            changePercentTextView.text = viewModel.getPercent()?.formatToPercent()
            with(changePercentTextView) {
                setTextColor(
                    requireContext().findColorInt(
                        if (viewModel.getPercent()!! < 0) {
                            R.color.red
                        } else {
                            R.color.green_light
                        }
                    )
                )
            }
            with(logoImageView) {
                load(createImageUrl(viewModel.getSymbol())) {
                    transformations(CircleCropTransformation())
                    crossfade(true)
                    crossfade(500)
                }
            }
            backButton.setOnClickListener {
                this@StockDetailFragment.findNavController().popBackStack()
            }

        }
    }

    private fun setupListeners() {

    }

    private fun setupChart() {
        with(binding.lineChart) {
            setTouchEnabled(true)
            isClickable = false
            isDoubleTapToZoomEnabled = false
            setDrawBorders(false)
            setDrawGridBackground(false)
            description.isEnabled = false
            legend.isEnabled = false
            axisLeft.setDrawGridLines(false)
            axisLeft.setDrawLabels(false)
            axisLeft.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            xAxis.setDrawAxisLine(false)
            axisRight.setDrawGridLines(false)
            axisRight.setDrawLabels(false)
            axisRight.setDrawAxisLine(false)
            setTouchEnabled(false)
            setGridBackgroundColor(requireContext().findColorInt(R.color.black_light))
        }

    }

    private fun setData(tradeUI: List<TradeUI>) {

        val values: MutableList<Entry> = mutableListOf()
        tradeUI.forEachIndexed { i, tradeIn ->
            values.add(
                Entry(
                    i.toFloat(),
                    tradeIn.price.toFloat(),
                    context?.findDrawable(R.drawable.icv_account)
                )
            )
        }
        setupChart()

        val dataSets: MutableList<ILineDataSet> = mutableListOf(
            createDataSet(values)
        )
        val lineData = LineData(dataSets).apply {
            setDrawValues(false)
            isHighlightEnabled = false
        }

        with(binding.lineChart) {
            data = lineData
            visible()
        }
    }

    private fun createDataSet(values: MutableList<Entry>): LineDataSet =
        LineDataSet(values, "DataSet 1").apply {
            disableDashedHighlightLine()
            setDrawIcons(false)
            setDrawValues(false)
            disableDashedLine()
            resetCircleColors()
            color = requireContext().findColorInt(
                if (viewModel.getPercent()!! < 0) {
                    R.color.red
                } else {
                    R.color.green_light
                }
            )
            setCircleColor(
                requireContext().findColorInt(
                    R.color.transparent
                )
            )
            setDrawCircleHole(false)
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f
            valueTextSize = 9f
            enableDashedHighlightLine(10f, 5f, 0f)
            setDrawFilled(true)
            fillDrawable = requireContext().findDrawable(
                if (viewModel.getPercent()!! < 0) {
                    R.drawable.red_gradient
                } else {
                    R.drawable.green_gradient
                }
            )
        }
}
