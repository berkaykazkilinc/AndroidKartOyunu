package com.berkaykazkilinc.composedeneme.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.ComposeDenemeTheme
import com.example.myapplication.viewmodel.TekOyuncuViewModel2

@Composable
fun OyunEkrani(navController: NavController) {
    val vivimodel: TekOyuncuViewModel2 = viewModel()
    val secilen_index = remember { mutableStateOf(0) }
    val secilen_index2 = remember { mutableStateOf(0) }
    val liste1 = listOf("Tek Oyuncu","Çok Oyunculu")
    val liste2 = listOf("2*2","4*4","6*6")
    var sayi=0

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row() {

            liste1.forEachIndexed { index, secim ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (secim == liste1[secilen_index.value]),
                        onClick = {
                            secilen_index.value=index
                            Log.e("Radio Button Secildi",secim)
                        })
                    Text(text = secim)
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {

            liste2.forEachIndexed { index, secim2 ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (secim2 == liste2[secilen_index2.value]),
                        onClick = {
                            secilen_index2.value=index
                            Log.e("Radio Button Zorluk Secildi",secim2)
                        })
                    Text(text = secim2)
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {

            Log.e("Radio Oyuncu Secim Son Durum : ",liste1[secilen_index.value])
            Log.e("Radio Zorluk Secim Son Durum : ",liste2[secilen_index2.value])
            if(secilen_index2.value==0){
                sayi=2
            }
            if(secilen_index2.value==1){
                sayi=4
            }
            if(secilen_index2.value==2){
                sayi=6
            }
            if(secilen_index.value== 0){
                navController.navigate("tekoyuncu/$sayi")
            }
            else{
                navController.navigate("cokoyuncu/$sayi")
            }

        }) {
            Text(text = "Başla")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OyunEkraniPreview() {
    ComposeDenemeTheme {

    }
}