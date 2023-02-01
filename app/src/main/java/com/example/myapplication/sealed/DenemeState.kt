package com.example.myapplication.sealed

import com.example.myapplication.model.User

sealed class DenemeState {
        class Success(val data: MutableList<User>) : DataState()
        class Failure(val message: String) : DataState()
        object Loading : DataState()
        object Empty : DataState()
}