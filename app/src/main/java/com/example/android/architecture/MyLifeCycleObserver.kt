package com.example.android.architecture

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

private const val TAG = "MyLifeCycleObserver"

class MyLifeCycleObserver : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreateEvent() {
            Log.e(TAG, "on create event")
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun inStartEvent() {
        Log.e(TAG, "on start event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent() {
        Log.e(TAG, "on resume event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent() {
        Log.e(TAG, "on pause event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent() {
        Log.e(TAG, "on stop event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent() {
        Log.e(TAG, "on destroy event")
    }
}