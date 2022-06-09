package com.kunalapk.uxcam

import androidx.recyclerview.widget.RecyclerView
import com.kunalapk.uxcam.databinding.ItemTestBinding

class TestViewHolder(private val binding: ItemTestBinding) :
    RecyclerView.ViewHolder(binding.root) {

    internal fun bind(testModel: TestModel,position:Int) {
        binding.tvValue.text = "Position : ${position}"

        if(position%2==1){
            UxCamUtils.hideViewsFromUxCam(binding.tvValue)
        }else{
            UxCamUtils.unHideViewsFromUxCam(binding.tvValue)
        }
    }
}