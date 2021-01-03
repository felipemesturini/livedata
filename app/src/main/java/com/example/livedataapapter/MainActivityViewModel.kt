package com.example.livedataapapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    private val mItens = mutableListOf<Flight>()
    val itens: MutableLiveData<List<Flight>> = MutableLiveData()

    fun addItem(flight: Flight) {
        mItens.add(flight)
        itens.postValue(mItens)
    }

    fun fetchData() {
        viewModelScope.launch {
            mItens.addAll(FlightDummy.data())
            itens.postValue(mItens)

        }
    }
}