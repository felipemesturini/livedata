package com.example.livedataapapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.serpro69.kfaker.Faker


private const val TAG = "FlightAdapter01"
class FlightAdapter01(val event: OnClickItem) : RecyclerView.Adapter<FlightAdapter01.FlightVH>(){
    private val mItems = mutableListOf<Flight>()

    inner class FlightVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val description = itemView.findViewById<TextView>(R.id.flightTextView)
        val local = itemView.findViewById<TextView>(R.id.destTextView)
        val tempo = itemView.findViewById<TextView>(R.id.timerTextView)
        fun bind(item: Flight) {
            description.text = item.descricao
            local.text = item.localizacao
            tempo.text = item.horario
        }

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                Log.i(TAG, mItems[pos].descricao)
                val item = mItems[pos]
                event.OnClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightVH {
        return parent.inflate()
    }

    override fun onBindViewHolder(holder: FlightVH, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = mItems.size

    private fun ViewGroup.inflate(): FlightAdapter01.FlightVH {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.produto_item_row, this, false)
        return FlightVH(view)
    }

    fun submitList(list: List<Flight>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }

}


