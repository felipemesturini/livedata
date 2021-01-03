package com.example.livedataapapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlightDetailViewModel(app: Application): AndroidViewModel(app) {
    private val _isLoading = MutableLiveData<Boolean>()

    var item: MutableLiveData<Flight> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    internal fun fetchItem(id: Long) {


        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _isLoading.postValue(true)
            }
            val flight = FlightDummy.data().first { it.codigo.equals(id) }
            delay(10000)
            item.postValue( flight )
            withContext(Dispatchers.Main) {
                _isLoading.postValue(false)
            }

        }

    }
}