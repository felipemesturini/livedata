package com.example.livedataapapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    private var _flights: LiveData<List<Flight>>

    private val mItens = mutableListOf<Flight>()
    val itens: LiveData<List<Flight>>
        get() = _flights

    init {
        val db = DbHelper.getInstance(getApplication(), viewModelScope)
        _flights = db.flightDao.list()
    }

    fun addItem(flight: Flight) {
        mItens.add(flight)
        //itens.postValue(mItens)
    }

    fun fetchData() {

    }
}