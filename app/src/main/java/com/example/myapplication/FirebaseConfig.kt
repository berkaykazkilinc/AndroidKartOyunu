package com.example.myapplication

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class FirebaseConfig {

        private val firestore = FirebaseFirestore.getInstance()

        @OptIn(ExperimentalCoroutinesApi::class)
        fun getData() = callbackFlow {

            val collection = firestore.collection("deneme")
            val snapshotListener = collection.addSnapshotListener { value, error ->
                this.trySend(value).isSuccess
            }

            awaitClose {
                snapshotListener.remove()
            }
        }
}
