package ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.scoresheetw2.ui.theme.ScoreSheetW2Theme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun add(a: Double, b: Double): Double = a + b
fun subtract(a: Double, b: Double): Double = a - b
fun multiply(a: Double, b: Double): Double = a * b
fun divide(a: Double, b: Double): String = if (b != 0.0) (a / b).toString() else "Không thể chia cho 0"
class Exercise1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreSheetW2Theme {
                Exercise1Screen()
                HeaderTitleBox("Exercise 1")
            }
        }
    }

    @Composable

    fun HeaderTitleBox(title: String) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFF263685))
        ) {
            Text(
                text = "Exercise 1",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 70.dp)
            )
        }
    }
    @Composable
    fun Exercise1Screen() {
        var num1Str by remember { mutableStateOf("") }
        var num2Str by remember { mutableStateOf("") }
        var resultStr by remember { mutableStateOf("Chưa có kết quả") }

        val activity = LocalContext.current

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )

        {
            Column(
                modifier = Modifier.align(Alignment.Center).fillMaxSize().padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(250.dp))

                Text(text = "Chọn phép toán")

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = num1Str,
                    onValueChange = { num1Str = it },
                    label = {Text(text = "Enter a number")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val a = num1Str.toDoubleOrNull()?: 0.0
                            val b = num2Str.toDoubleOrNull()?: 0.0
                            resultStr = add(a, b).toString()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),

                    ) {
                        Text(text = "+")
                    }
                    Button(
                        onClick = {
                            val a = num1Str.toDoubleOrNull()?: 0.0
                            val b = num2Str.toDoubleOrNull()?: 0.0
                            resultStr = subtract(a, b).toString()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9800),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "-")
                    }
                    Button(
                        onClick = {
                            val a = num1Str.toDoubleOrNull()?: 0.0
                            val b = num2Str.toDoubleOrNull()?: 0.0
                            resultStr = multiply(a, b).toString()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9C27B0),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "x")
                    }
                    Button(
                        onClick = {
                            val a = num1Str.toDoubleOrNull()?: 0.0
                            val b = num2Str.toDoubleOrNull()?: 0.0
                            resultStr = divide(a, b).toString()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "/")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = num2Str,
                    onValueChange = { num2Str = it },
                    label = {Text(text = "Enter a number")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Kết quả: $resultStr", textAlign = TextAlign.Start, modifier = Modifier.offset(x = (-100).dp))
            }

            Button(
                onClick = {
                    (activity as Activity).finish()},
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
            ) {
                Text(text = "Back Home")
            }
            }

            }
        }