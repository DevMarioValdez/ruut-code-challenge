package mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.mariovaldez.ruutcodechallenge.databinding.ViewOptionItemBinding
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.OptionUI

internal class OptionsAdapter(
    private val listener: (String) -> Unit,
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    private val options: MutableList<OptionUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewOptionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit =
        holder.bind(options[position])

    override fun getItemCount(): Int = options.size

    @SuppressLint("NotifyDataSetChanged")
    fun addOptions(options: List<OptionUI>) {
        this.options.clear()
        this.options.addAll(options)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ViewOptionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: OptionUI) = with(binding) {
            iconImageView.setImageDrawable(option.icon)
            labelTextView.text = option.label
            root.setOnClickListener { listener(option.id) }
        }
    }
}
