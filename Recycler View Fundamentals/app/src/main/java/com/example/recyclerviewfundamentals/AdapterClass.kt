package com.example.recyclerviewfundamentals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewfundamentals.databinding.EachItemLayoutBinding
import java.util.ArrayList

class AdapterClass (private val dataList : ArrayList<DataClass>):
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

        //IMPLEMENTING VIEW BINDING HERE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = EachItemLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.companyImg.setImageResource(currentItem.dataImg)
        holder.binding.companyName.text = currentItem.dataName
        holder.binding.companyDetails.text = currentItem.dataDescription
    }

    //CREATED OUR VIEW HOLDER CLASS
    class MyViewHolder(val binding:EachItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}