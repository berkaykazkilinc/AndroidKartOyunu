package com.example.myapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Matris
import com.example.myapplication.model.User
import com.example.myapplication.sealed.FuskaState
import com.example.myapplication.sealed.DataState
import com.example.myapplication.sealed.DenemeState
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel() {

    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebaseStreamMapp()
    }
    private fun fetchDataFromFirebase() {
        val tempList = mutableListOf<String>()
        response.value = DataState.Loading
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").get()
            .addOnSuccessListener { result ->
                tempList.clear()
                tempList.add(result.data?.getValue("matris").toString())
                response.value = DataState.Success(tempList)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    private fun fetchDataFromFirebaseDeneme() {
        val tempList = mutableListOf<String>()
        response.value = DataState.Loading
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").get()
            .addOnSuccessListener { result ->
                tempList.clear()
                val dizi = result.data?.getValue("matris") as List<*>
                //tempList.add(result.data?.getValue("matris").toString())
                for (i in dizi) {
                    tempList.add(i.toString())
                }
                response.value = DataState.Success(tempList)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    private fun fetchDataFromFirebaseStream() {
        val tempList = mutableListOf<String>()
        response.value = DataState.Loading
        FirebaseFirestore.getInstance().collection("deneme")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                tempList.clear()
                for (doc in value!!) {
                    doc.getString("den")?.let {

                        tempList.add(it.toString())
                        println(tempList)
                    }
                    response.value = DataState.Success(tempList)
                }
            }
    }

    private fun fetchDataFromFirebaseStreamDeneme() {
        val tempList = mutableListOf<String>()
        response.value = DataState.Loading
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                tempList.clear()
                val dizi = value?.data?.getValue("matris") as List<*>
                for (i in dizi) {
                    tempList.add(i.toString())
                }
                response.value = DataState.Success(tempList)
            }
    }

    fun fetchDataFromFirebaseStreamMap() {
        val tempList = mutableListOf<User>()
        var gonder: Matris
        response.value = DenemeState.Loading
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                tempList.clear()

                if (value != null) {

                    val dizii = value.toObject(Matris::class.java)
                    if (dizii != null) {
                        val users = dizii.matris
                        if (users != null) {
                            for (user in users) {
                                if (user.tur != null && user.secim != null) {
                                    tempList.add(user)
                                }

                                println(user.tur)
                                println(user.secim)

                                //Do what you need to to do with your List<City>.
                            }
                        }
                    }
                    response.value = DenemeState.Success(tempList)

                }
                /*
                val dizi = value?.data?.getValue("matris")

                response.value = DenemeState.Success(tempList)

            }*/

            }
    }

    fun fetchDataFromFirebaseStreamMapp() {
        val tempList = mutableListOf<Matris>()
        response.value = FuskaState.Loading
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                tempList.clear()

                if (value != null) {

                    val dizii = value.toObject(Matris::class.java)
                    if (dizii != null) {
                        tempList.add(dizii)
                    }
                    response.value = FuskaState.Success(tempList)

                }
                /*
                val dizi = value?.data?.getValue("matris")

                response.value = DenemeState.Success(tempList)

            }*/

            }
    }
    fun fetchDataFromFirebaseDenemee1(tur: String,sure: String) {
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").get()
            .addOnSuccessListener { result ->
                val document = result
                if (document != null) {
                    val doc = document.toObject(Matris::class.java)
                    if (doc != null) {
                        val users = doc.matris
                        if (users != null) {
                            for (user in users) {
                                if (user.tur != null && user.secim != null) {

                                    if (user.tur == tur){
                                        println("bakılan "+ user.tur)
                                        if(user.secim== "false"){
                                            user.secim = "true"
                                        }else{
                                            user.secim = "false"
                                        }

                                    }
                                }
                                println(user.tur)
                                println(user.secim)
                            }
                        }
                    }

                    val docData = hashMapOf(
                        "matris" to doc?.matris,
                        "sure" to sure
                    )
                    FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").set(docData)
                }

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
    fun fetchDataFromFirebaseDenemee(tur: String) {
        FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").get()
            .addOnSuccessListener { result ->
                val document = result
                if (document != null) {
                    val doc = document.toObject(Matris::class.java)
                    if (doc != null) {
                        val users = doc.matris
                        if (users != null) {
                            for (user in users) {
                                if (user.tur != null && user.secim != null) {

                                    if (user.tur == tur){
                                        println("bakılan "+ user.tur)
                                        user.secim = "true"
                                    }
                                }
                                println(user.tur)
                                println(user.secim)
                            }
                        }
                    }

                    val docData = hashMapOf(
                        "matris" to doc?.matris,
                    )
                    FirebaseFirestore.getInstance().collection("deneme").document("8aG0A5My2hOafaZvNDOh").set(docData)
                }

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }


}


