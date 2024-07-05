package com.example.databindingusingmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val quoteLiveData = MutableLiveData("This is the fact")

    fun updateQuote(){
        quoteLiveData.value = "This is the updated Fact"
    }
}