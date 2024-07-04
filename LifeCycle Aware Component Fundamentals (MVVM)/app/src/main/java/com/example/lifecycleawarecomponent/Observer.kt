package com.example.lifecycleawarecomponent

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Observer : LifecycleObserver  {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
         Log.d("Main" , "OBSERVER ON-CREATE CALLED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.d("Main" , "OBSERVER ON-RESUME CALLED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.d("Main" , "OBSERVER ON-PAUSE CALLED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.d("Main" , "OBSERVER ON-STOP CALLED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.d("Main" , "OBSERVER ON-DESTROY CALLED")
    }
}