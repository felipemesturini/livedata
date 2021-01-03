package com.example.livedataapapter

object FlightDummy {
    fun data(): List<Flight> {
        return listOf(
            Flight(1, "Voo para Sao Paulo", "Sai de POA para SPO", "Parida 12:00", "poa_spo.png"),
            Flight(2, "Voo para Brasilia", "Sai do RJ para BR", "Partida 13:00", "dado.png"),
            Flight(3, "Voo para Porto Alegre", "Sai de SP para RS", "Partida 21:00", "dado.png"),
            Flight(4, "Pacote para Portugal", "Sai de SP para PT", "Partida 23:00", "dado.png"),
            Flight(5, "Pacote Fortaleza", "Saida de RS para CE", "Partida 21:00", "dado.png")
        )
    }
}