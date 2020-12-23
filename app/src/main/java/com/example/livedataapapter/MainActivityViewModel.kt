package com.example.livedataapapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    val mItens = mutableListOf<Produto>()
    val itens: LiveData<List<Produto>>
        get() {
            return MutableLiveData( mItens )
        }

    fun addItem(produto: Produto) {
        this.mItens.add(produto)
    }
}