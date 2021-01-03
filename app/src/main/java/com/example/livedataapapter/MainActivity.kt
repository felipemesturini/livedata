package com.example.livedataapapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mViewModel.itens.observe(this, Observer { list ->
            (mRecyclerView.adapter as FlightAdapter01).submitList(list)
        })
        setupRecyclerView()
        mViewModel.fetchData()
    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById(R.id.flightRecyclerView)
        mRecyclerView.apply{
            adapter = FlightAdapter01(object : OnClickItem {
                override fun OnClick(item: Flight) {
                    Log.i(TAG, item.descricao)
                    val nav = Intent(this@MainActivity, FlightDetailActivity::class.java)
                    nav.putExtra(KEY_ID, item.codigo)
                    startActivity(nav)
//                    val faker = Faker()
//                    val flight  = Flight(
//                        (mRecyclerView.adapter!!.itemCount + 1).toLong(),
//                        faker.artist.names(),
//                        faker.lorem.words(),
//                        faker.lorem.words(),
//                        faker.animal.name()
//                    )
//
//                    mViewModel.addItem(flight)
                }
            })
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))
        }
    }
}