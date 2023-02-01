package com.example.myapplication.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Matris
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class AuthViewModel {

    private val _something: MutableLiveData<String> = MutableLiveData("")
    val something: LiveData<String> = _something

    fun SıgnIn(girilen: String): String {
        FirebaseFirestore.getInstance().collection("kullanici").whereEqualTo("kullanici_adi",girilen).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document!=null){
                        //println("bu mu" + document.get("email").toString())
                        _something.value= document.get("pass").toString()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        return something.value ?: ""
    }

    fun SıgnUp(email: String, pass: String, username: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
        val uid: String = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val docData = hashMapOf(
            "kullanici_adi" to username,
            "id" to uid,
            "pass" to pass,
            "email" to email
        )
        FirebaseFirestore.getInstance().collection("kullanici").document(uid).set(docData)
    }

    fun ResetPassword(oldpass: String, pass: String, username: String){
        var newPass: String = pass
        var passo: String = ""
        var uid: String =""
        FirebaseFirestore.getInstance().collection("kullanici").whereEqualTo("kullanici_adi",username).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document!=null){
                        println("bu mu" + document.get("pass").toString())
                        println("bu muuu" + document.get("id").toString())
                        passo = document.get("pass").toString()
                        uid = document.get("id").toString()
                        println("eski1 " + passo + " eski2 "+ oldpass + " yeni "+ newPass)
                        if (passo == oldpass){
                            FirebaseFirestore.getInstance().collection("kullanici").document(uid).update("pass",newPass)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        val docData = hashMapOf(
            "pass" to newPass
        )
        println("eski1 " + passo + " eski2 "+ oldpass + " yeni "+ newPass)
        if (passo == oldpass){
            FirebaseFirestore.getInstance().collection("kullanici").document(uid).update("pass",newPass)
        }
    }

    fun updateSomething() {
        _something.value = "new value"
    }

    fun getSomeValue(): String {
        return something.value ?: ""
    }
}