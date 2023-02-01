package com.example.myapplication.sealed

import com.example.myapplication.model.User

sealed class DataState {
    class Success(val data: MutableList<String>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}