package com.kunalapk.uxcam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var testAdapter:TestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UxCamUtils.startUxCamSession(this,"testProduct")

        findViewById<RecyclerView>(R.id.rvTest).apply {
            adapter = TestAdapter().apply {
                addItems(arrayListOf<TestModel>().apply {
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                    add(TestModel(System.currentTimeMillis().toString()))
                })
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}