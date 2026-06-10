package com.example.campsitecommander

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay



data class CampItem(
    val name: String,
    val category: String,
    val quantity: String,
    val comments: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            var screen by remember { mutableStateOf("welcome") }
            val itemList = remember { mutableStateListOf<CampItem>() }

            MaterialTheme(colorScheme = darkColorScheme()) {

                when (screen) {

                    // WELCOME SCREEN
                    "welcome" -> {

                        Box(modifier = Modifier.fillMaxSize()) {

                            Image(
                                painter = painterResource(id = R.drawable.ollaa),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.4f))
                            )

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Campsite Commander",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Button(
                                    onClick = { screen = "loading" },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF6F00),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("START")
                                }
                            }
                        }
                    }

                   // LOADING SCREEN
                    "loading" -> {

                        LaunchedEffect(Unit) {
                            delay(3000)
                            screen = "main"
                        }

                        Box(modifier = Modifier.fillMaxSize()) {

                            Image(
                                painter = painterResource(id = R.drawable.ollaa),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            )

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                CircularProgressIndicator(color = Color(0xFFFF6F00))

                                Spacer(modifier = Modifier.height(15.dp))

                                Text(
                                    text = "Loading...",
                                    color = Color.White
                                )
                            }
                        }
                    }


                    "main" -> {

                        var name by remember { mutableStateOf("") }
                        var category by remember { mutableStateOf("") }
                        var quantity by remember { mutableStateOf("") }
                        var comments by remember { mutableStateOf("") }

                        Box(modifier = Modifier.fillMaxSize()) {

                            Image(
                                painter = painterResource(id = R.drawable.ollaa),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Text(
                                    text = "Add Camping Item",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                OutlinedTextField(
                                    value = name,
                                    onValueChange = { name = it },
                                    label = { Text("Item Name", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF6F00),
                                        unfocusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = category,
                                    onValueChange = { category = it },
                                    label = { Text("Category", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF6F00),
                                        unfocusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = quantity,
                                    onValueChange = { quantity = it },
                                    label = { Text("Quantity", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF6F00),
                                        unfocusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = comments,
                                    onValueChange = { comments = it },
                                    label = { Text("Comments", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFFF6F00),
                                        unfocusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Button(
                                    onClick = {
                                        if (name.isNotBlank() && category.isNotBlank() && quantity.isNotBlank()) {
                                            itemList.add(CampItem(name, category, quantity, comments))
                                            name = ""; category = ""; quantity = ""; comments = ""
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Add Gear")
                                }

                                Button(
                                    onClick = { screen = "list" },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("View Full list of Gear")
                                }
                            }
                        }
                    }


                    "list" -> {

                        Box(modifier = Modifier.fillMaxSize()) {

                            Image(
                                painter = painterResource(id = R.drawable.ollaa),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {

                                Text(
                                    text = "Stored Items",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                LazyColumn(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    items(itemList) { item ->

                                        Card(
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color.Black.copy(alpha = 0.6f)
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 10.dp)
                                        ) {

                                            Column(modifier = Modifier.padding(12.dp)) {

                                                Text("Name: ${item.name}", color = Color.White)
                                                Text("Category: ${item.category}", color = Color.White)
                                                Text("Quantity: ${item.quantity}", color = Color.White)
                                                Text("Comments: ${item.comments}", color = Color.White)
                                            }
                                        }
                                    }
                                }

                                Button(
                                    onClick = { screen = "main" },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF6F00)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Back to Base")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}