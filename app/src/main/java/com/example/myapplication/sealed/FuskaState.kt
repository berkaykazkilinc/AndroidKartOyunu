package com.example.myapplication.sealed

import com.example.myapplication.model.Matris

sealed class FuskaState {
    class Success(val data: MutableList<Matris>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}