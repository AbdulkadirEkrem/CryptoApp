package com.abdulkadirekrem.cryptoappapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkadirekrem.cryptoappapi.model.CryptoData
import com.abdulkadirekrem.cryptoappapi.network.RetrofitClient
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val _cryptoList = MutableLiveData<List<CryptoData>>()
    val cryptoList: LiveData<List<CryptoData>> = _cryptoList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCryptoData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getCryptoData()
                if (response.isSuccessful) {
                    _cryptoList.postValue(response.body())
                    _error.postValue(null)
                } else {
                    _error.postValue("Hata: ${response.code()}")
                    Log.e("API", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Bağlantı hatası: ${e.message}")
                Log.e("API", "Exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}