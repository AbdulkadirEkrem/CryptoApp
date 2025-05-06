package com.abdulkadirekrem.cryptoappapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abdulkadirekrem.cryptoappapi.R
import com.abdulkadirekrem.cryptoappapi.databinding.ItemCryptoBinding
import com.abdulkadirekrem.cryptoappapi.model.CryptoData

class CryptoAdapter(private val cryptoList: List<CryptoData>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    inner class CryptoViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crypto: CryptoData) {
            binding.tvName.text = crypto.name
            binding.tvSymbol.text = crypto.symbol.uppercase()
            binding.tvPrice.text = "$${formatPrice(crypto.currentPrice)}"

            // Fiyat değişimini işle ve rengini ayarla
            val priceChange = crypto.priceChangePercentage24h
            if (priceChange != null) {
                val changeText = String.format("%.2f%%", priceChange)
                binding.tvPriceChange.text = changeText

                // Artış/azalış durumuna göre renk ayarla
                val textColor = if (priceChange >= 0) {
                    binding.tvPriceChange.text = "+$changeText"
                    ContextCompat.getColor(binding.root.context, R.color.price_up)
                } else {
                    ContextCompat.getColor(binding.root.context, R.color.price_down)
                }
                binding.tvPriceChange.setTextColor(textColor)
            } else {
                binding.tvPriceChange.text = "-"
                binding.tvPriceChange.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                )
            }



        }

        private fun formatPrice(price: Double): String {
            return when {
                price >= 1000 -> String.format("%,.2f", price)
                price >= 1 -> String.format("%.2f", price)
                else -> String.format("%.6f", price)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = ItemCryptoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(cryptoList[position])
    }

    override fun getItemCount() = cryptoList.size
}