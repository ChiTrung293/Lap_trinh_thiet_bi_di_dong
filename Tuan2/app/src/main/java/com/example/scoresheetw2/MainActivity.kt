package com.example.scoresheetw2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.scoresheetw2.ui.theme.ScoreSheetW2Theme
import ui.Exercise1Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.Exercise2Activity
import ui.Exercise3Activity
import ui.SummaryActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreSheetW2Theme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current

Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center)
{
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BÀI TẬP THỰC HÀNH ",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 8.dp)
                .background(Color(0xAE25426E))
                .clip(RoundedCornerShape(8.dp)),
            color = Color.White,

        )
        Text(
            text = "If, for và nullable",
                    fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            val intent = Intent(context, Exercise1Activity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Exercise 1",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            val intent = Intent(context, Exercise2Activity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Exercise 2",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            val intent = Intent(context, Exercise3Activity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Exercise 3",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            val intent = Intent(context, SummaryActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "SummaryActivity",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }
    }
}
    }


