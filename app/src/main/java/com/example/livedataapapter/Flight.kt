package com.example.livedataapapter

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tb_flights")
data class Flight(
    @PrimaryKey
    val codigo: Long,
    val descricao: String,
    val localizacao: String,
    val horario: String,
    val imagem: String,
    val lastUpdate: Date
)
