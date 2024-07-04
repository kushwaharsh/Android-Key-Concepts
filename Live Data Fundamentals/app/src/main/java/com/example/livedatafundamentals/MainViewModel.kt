package com.example.livedatafundamentals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {


    private val factsLiveDataObject = MutableLiveData<String>("This is mutable live data")

    val factsLiveData : LiveData<String>
        get() = factsLiveDataObject

    fun updateLiveData(){
        //Value property is used to update the live data
        factsLiveDataObject.value = "Updated Live Data"
    }
}