package com.example.myapplication

import android.content.ContentValues
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Card
import com.google.firebase.firestore.FirebaseFirestore

class CardlarDataRepo {
    var cardlistesi = MutableLiveData<MutableList<Card>>()
    var acikcardlistesi = MutableLiveData<List<Card>>()

    init {
        cardlistesi = MutableLiveData()
        acikcardlistesi = MutableLiveData()
    }



    /* Ev katsayıları
     ▪ Gryffindor : 2
     ▪ Slytherin : 2
     ▪ Hufflepuff : 1
     ▪ Ravenclaw : 1
     */

    fun CardlariGetir():MutableLiveData<MutableList<Card>>{
        return cardlistesi
    }

    /*fun AcikCardlariGetir():MutableLiveData<List<Card>>{
        return acikcardlistesi
    }*/

    fun TumCardlariAl(){
        val liste = mutableListOf<Card>()

        val c1 = Card(1,"Harry Potter",10,"SLYTHERİN",2,false,false)
        val c2 = Card(2,"Tom Riddle",20,"SLYTHERİN",2,false,false)
        val c8 = Card(8,"Tom Riddle",20,"SLYTHERİN",2,false,false)
        val c3 = Card(3,"Harry Potter",10,"SLYTHERİN",2,false,false)
        val c4 = Card(4,"Albus Dumbledore",20,"GRYFFİNDOR",2,false,false)
        val c5 = Card(5,"Luna Lovegood",9,"RAVENCLAW",1,false,false)
        val c6 = Card(6,"Helga Hufflepuff",20,"HUFFLEPUFF",1,false,false)
        val c7 = Card(7,"Fat Friar",6,"HUFFLEPUFF",1,false,false)

        liste.add(c1)
        liste.add(c2)
        liste.add(c8)
        liste.add(c3)
        liste.add(c4)
        liste.add(c5)
        liste.add(c6)
        liste.add(c7)

        liste.apply { shuffle() }

        cardlistesi.value = liste
    }

    fun deneme(){
        val liste = mutableListOf<Card>()
        FirebaseFirestore.getInstance().collection("kart").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var aa = document.get("card_karakter_puan").toString()
                    if(document!=null){
                        if (aa.isNullOrBlank() ||
                            aa.isEmpty() ||
                            aa.isNullOrEmpty() ||
                            !aa.isDigitsOnly() ||
                            aa.toIntOrNull() == null
                        ) {

                        }else{
                            if(document.get("card_ev").toString() == "Gryffindor" || document.get("card_ev").toString() == "Slytherin"){
                                val c1 = Card(1, document.get("card_karakteri").toString(),aa.toInt(),document.get("card_ev").toString(),2,true,false)
                                liste.add(c1)
                            }else{
                                val c1 = Card(1, document.get("card_karakteri").toString(),aa.toInt(),document.get("card_ev").toString(),1,true,false)
                                liste.add(c1)
                            }
                            println("buubbura mı"+ document.get("card_karakteri").toString())
                            //val c1 = Card(1, document.get("card_karakteri").toString(),aa.toInt(),document.get("card_ev").toString(),2,true,false)

                        }
                        //println("bu mu" + document.get("email").toString())

                    }
                }
                println("yoksa bura mı "+ liste)
                liste.apply { shuffle() }
                cardlistesi.value = liste
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }
    /* fun AcikCardlariAl(){
         val liste = mutableListOf<Card>()

         for(i in 0..7){
             if(cardlistesi.value!![i].card_acik_mi==true && cardlistesi.value!![i].card_eslesti_mi==false){
                 liste.add(cardlistesi.value!![i])
             }
         }

         acikcardlistesi.value = liste
     }*/




}
