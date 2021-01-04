package com.example.livedataapapter

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FlightDao {
    @Insert
    fun insert(flight: Flight): Unit
    @Query(value = "select * from tb_flights order by descricao")
    fun list(): LiveData<List<Flight>>
    @Query(value = "select * from tb_flights where codigo = :key")
    fun get(key: Long): Flight
    @Query(value = "delete from tb_flights")
    fun clear()
}
