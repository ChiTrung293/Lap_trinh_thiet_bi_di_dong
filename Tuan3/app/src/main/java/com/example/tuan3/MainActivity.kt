package com.example.tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan3.ui.theme.Tuan3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan3Theme {
                val currentScreen = remember { mutableStateOf("home") }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen.value) {
                        "home" -> Greeting(
                            modifier = Modifier.padding(innerPadding),
                            onNavigateNext = { currentScreen.value = "ui_components_list" }
                        )
                        "ui_components_list" -> UIComponentsListScreen(
                            onBack = { currentScreen.value = "home" }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    onNavigateNext: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_icon),
            contentDescription = "Jetpack Compose Icon",
            modifier = Modifier.size(280.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jetpack Compose",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for\n" +
                    "building native Android applications using\n" +
                    "a declarative programming approach.",
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onNavigateNext() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A97F3),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "I'm ready",
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
