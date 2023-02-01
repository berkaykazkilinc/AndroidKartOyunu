package com.example.myapplication

import android.view.View
import androidx.lifecycle.ViewModel

class DataViewModel(val repo :FirebaseConfig): ViewModel() {

    fun getInfo(){
        repo.getData()
    }
}