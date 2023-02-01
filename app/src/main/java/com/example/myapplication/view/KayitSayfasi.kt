package com.example.myapplication.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ComposeDenemeTheme
import com.example.myapplication.viewmodel.AuthViewModel

@Composable
fun KayitSayfasi(model: AuthViewModel) {
    val tf_1 = remember { mutableStateOf("") }//text field 1
    val tf_2 = remember { mutableStateOf("") }//text field 2
    val tf_3 = remember { mutableStateOf("") }//text field 2
    val kullanici_adi = remember { mutableStateOf("") }
    val sifre = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = tf_1.value,
            onValueChange = { tf_1.value = it },
            label = { Text(text = "Kullanici Adinizi Giriniz :") },


        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = tf_3.value,
            onValueChange = { tf_3.value = it },
            label = { Text(text = "Email Adresinizi Giriniz :") },


            )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = tf_2.value,
            onValueChange = { tf_2.value = it },
            label = { Text(text = "Sifrenizi Giriniz :") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            kullanici_adi.value = tf_1.value
            sifre.value = tf_2.value
            email.value = tf_3.value
            model.SıgnUp(email.value,sifre.value,kullanici_adi.value)
            Log.e("kayit","Kayit İslemi Tamamlandi")
        }) {
            Text(text = "Kayit Ol")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KayitSayfasiPreview() {
    ComposeDenemeTheme {
        KayitSayfasi(model = AuthViewModel())
    }
}