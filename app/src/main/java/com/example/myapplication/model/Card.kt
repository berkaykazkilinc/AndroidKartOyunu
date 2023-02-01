package com.example.myapplication.model

data class Card(
    var card_id:Int?,
    var card_karakteri:String,
    var card_karakter_puan:Int?,
    var card_ev : String?,
    var card_ev_puan:Int?,
    var card_acik_mi:Boolean = true,
    var card_eslesti_mi:Boolean= false,
    var card_secildi_mi:Boolean= false
)
