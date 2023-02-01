package com.example.myapplication

import CokOyuncu
import KartEkle
import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import com.example.myapplication.sealed.DataState

import com.example.myapplication.view.KayitSayfasi
import com.example.myapplication.viewmodel.MainViewModel

import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkaykazkilinc.composedeneme.view.OyunEkrani
import com.berkaykazkilinc.composedeneme.view.SifreSayfasi
import com.berkaykazkilinc.composedeneme.view.TekOyuncu
import com.example.myapplication.ui.theme.ComposeDenemeTheme

import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.TekOyuncuViewModel2

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDenemeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SayfaGecisleri(viewModel)
                    //SetData(viewModel)
                }
            }
        }
    }
}

suspend fun readDataTestFinal(): String {
    val docRef = FirebaseFirestore.getInstance().collection("deneme")
        .document("deneme1")
    return suspendCoroutine { continuation ->
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    continuation.resume(document.get("den").toString())
                } else {
                    continuation.resume("No such document")
                }
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
}
suspend fun readDataTestFinall(): String {
    val docRef = FirebaseFirestore.getInstance().collection("kullanici").whereEqualTo("kullanici_adi","kaan")
    return suspendCoroutine { continuation ->
        docRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document != null) {
                        continuation.resume(document.get("email").toString())
                    } else {
                        continuation.resume("No such document")
                    }
                }
            }
            .addOnFailureListener { exception ->

            }
    }
}

@Composable
fun SetData(viewModel: MainViewModel) {
    when (val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            Column() {
                for(i in result.data){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                    ) {

                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = i.toString(),
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }

                    }
                }
            }
        }
        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error Fetching data",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                )
            }
        }
    }
}

@Composable
fun ShowLazyList(users: MutableList<String>) {
    LazyColumn {
        items(users) { food ->
            CardItem(food)
        }
    }
}

@Composable
fun CardItem(food: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = food,
                fontSize = MaterialTheme.typography.h5.fontSize,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }

    }
}

/*
@Composable
fun Greeting(viewModel: MainViewModel) {
    var newStringFromStoredData by remember {
        mutableStateOf("")
    }
    Text(text ="$newStringFromStoredData")

    LaunchedEffect(newStringFromStoredData) {
        newStringFromStoredData =
            try { readDataTestFinal() } catch(e: Exception) { "Error!" }
    }
}
*/

@Composable
fun SayfaGecisleri(viewModel: MainViewModel) {
    val vivimodel: TekOyuncuViewModel2 = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            AnaSayfa(navController = navController, model = AuthViewModel())
        }
        composable("kayitsayfasi"){
            KayitSayfasi(AuthViewModel())
        }
        composable("sifresayfasi"){
            //KartEkle()
            SifreSayfasi(AuthViewModel())
        }
        composable("oyunekrani"){
            //vivimodel.loadCards() //2
            vivimodel.loadCardsss() //4
            OyunEkrani(navController=navController)
        }
        composable("tekoyuncu/{sayi}", arguments = listOf(
            navArgument("sayi",{type= NavType.IntType})
        )){
            val sayi = it.arguments?.getInt("sayi")!!
            TekOyuncu(sayi = sayi,vivimodel)
        }
        composable("cokoyuncu/{sayi}", arguments = listOf(
            navArgument("sayi",{type= NavType.IntType})
        )){
            val sayi = it.arguments?.getInt("sayi")!!
            CokOyuncu(sayi = sayi,vivimodel)
        }

    }
}


@Composable

fun AnaSayfa(navController:NavController, model: AuthViewModel) {
    val context = LocalContext.current
    val tf_1 = remember { mutableStateOf("") }//text field 1
    val tf_2 = remember { mutableStateOf("") }//text field 2
    val kullanici_adi = remember { mutableStateOf("") }
    val sifre = remember { mutableStateOf("") }
    val vivimodel: TekOyuncuViewModel2 = viewModel()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = tf_1.value,
            onValueChange = {tf_1.value=it},
            label = { Text(text = "Kullanici Adinizi Giriniz :")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = tf_2.value,
            onValueChange = {tf_2.value=it},
            label = { Text(text = "Sifrenizi Giriniz :")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            kullanici_adi.value=tf_1.value
            sifre.value=tf_2.value
            println(vivimodel.cardlistesi.value)

            if(sifre.value == "" && kullanici_adi.value == "" ){
                Toast.makeText(context,"Lutfen Alanlari Doldurun",Toast.LENGTH_SHORT).show()
            }
            else if(sifre.value == model.SıgnIn(kullanici_adi.value)){
                navController.navigate("oyunekrani")
            }
        }) {
            Text(text = "Giris Yap")
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                navController.navigate("kayitsayfasi")
            }) {
                Text(text = "Kayit Ol")

            }

            Button(onClick = {
                navController.navigate("sifresayfasi")
            }) {
                Text(text = "Sifre Guncelle")
            }
        }
    }

}