package com.abdulkadirekrem.cryptoappapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdulkadirekrem.cryptoappapi.adapter.CryptoAdapter
import com.abdulkadirekrem.cryptoappapi.databinding.ActivityMainBinding
import com.abdulkadirekrem.cryptoappapi.model.CryptoData
import com.abdulkadirekrem.cryptoappapi.viewmodel.CryptoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar'ı ayarla
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // RecyclerView ayarları
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        // SwipeRefreshLayout ayarları
        binding.swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent
        )

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchCryptoData()
        }

        // ViewModel gözlemcileri
        viewModel.cryptoList.observe(this) { list ->
            list?.let {
                binding.recyclerView.adapter = CryptoAdapter(it)
                binding.swipeRefresh.isRefreshing = false
            }
        }

        // İlk veriyi yükle
        binding.swipeRefresh.isRefreshing = true
        viewModel.fetchCryptoData()
    }
}