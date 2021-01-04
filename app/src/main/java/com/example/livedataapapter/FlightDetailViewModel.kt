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
    private val _flight = MutableLiveData<Flight>()

    val item: LiveData<Flight>
        get() = _flight
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    internal fun fetchItem(id: Long) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val db = DbHelper.getInstance(getApplication(), viewModelScope)
                val dao = db.flightDao
                dao.clear()
                val flights = FlightDummy.data()
                flights.forEach {
                    dao.insert(it)
                }
                val flight = dao.get(id)
                delay(10000)
                _flight.postValue(flight)
            }
            _isLoading.postValue(false)
        }

    }
}