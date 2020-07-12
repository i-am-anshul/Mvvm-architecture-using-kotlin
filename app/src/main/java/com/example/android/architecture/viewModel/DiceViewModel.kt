package com.example.android.architecture.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.architecture.util.DiceHelper

class DiceViewModel(app: Application) : AndroidViewModel(app) {

    val headline = MutableLiveData<String>()
    val dice = MutableLiveData<IntArray>()
    val  context = app

    init {
        Log.e(TAG, "view model created " )
        headline.value = "Welcome"
        dice.value = intArrayOf(6,6,6,6,6)
    }

    fun rollDice(){
        dice.value = DiceHelper.rollDice()
        headline.value = DiceHelper.evaluateDice(context, dice.value)
    }
}