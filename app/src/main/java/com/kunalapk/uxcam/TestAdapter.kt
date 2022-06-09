package com.kunalapk.uxcam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunalapk.uxcam.databinding.ItemTestBinding

class TestAdapter : RecyclerView.Adapter<TestViewHolder>() {

    private var testModelList = mutableListOf<TestModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(testModelList[position],position)
    }

    override fun getItemCount(): Int = testModelList.size

    fun getItemList(): MutableList<TestModel> {
        return testModelList
    }

    fun addItems(testModelList:MutableList<TestModel>){
        this.testModelList = testModelList
        notifyDataSetChanged()
    }

}