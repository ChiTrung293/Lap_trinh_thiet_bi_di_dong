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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoresheetw2.ui.theme.ScoreSheetW2Theme

class Exercise3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreSheetW2Theme {
                HeaderTitleBox("Exercise 3")
                Exercise3Screen()

            }
        }
    }

    @Composable
    fun HeaderTitleBox(title: String) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFF25316E))
        ) {
            Text(
                text = "Exercise 3",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp)
            )
            Text(
                text = "Xếp loại học lực",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 70.dp)
            )
        }
    }
    val mark: Double = 8.5

    fun filter(
        mark: Double
    ): String {
        if (mark < 0 || mark > 10) {
            return "Không hợp lệ"
        }
        if (mark >= 8.0) {
            return "Giỏi"
        }
        if (mark >= 6.5) {
            return "Khá"
        }
        if (mark >= 5.0) {
            return "Trung bình"
        }

        return "Yếu"
    }

    fun getRankTextColor(rank: String): Color {
        if (rank == "Giỏi") {
            return Color(0xFF2C5620)
        } else if (rank == "Khá") {
            return Color(0xFF1565C0)
        } else if (rank == "Trung bình") {
            return Color(0xFF884F2F)
        } else if (rank == "Yếu") {
            return Color(0xFFC62828)
        } else {
            return Color.Gray
        }
    }

    fun getRankBgColor(rank: String): Color {
        if (rank == "Giỏi") {
            return Color(0x6B6BD24E)
        } else if (rank == "Khá") {
            return Color(0x462196F3)
        } else if (rank == "Trung bình") {
            return Color(0x46FF9800)
        } else if (rank == "Yếu") {
            return Color(0x46F44336)
        } else {
            return Color(0x229E9E9E)
        }
    }

    val mainRank = filter(mark)

    @Composable
    fun Exercise3Screen() {
        val activity = LocalContext.current
        Box(
            modifier = Modifier.fillMaxSize().padding(top = 120.dp),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(

                ) {
                    Column(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Điểm trung bình",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "${mark}",
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(1f)
                                .padding(vertical = 8.dp, horizontal = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0x6B6BD24E)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 15.dp, horizontal = 8.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Giỏi",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2C5620)

                                )
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "kiểm tra thêm"
                )
                Spacer(modifier = Modifier.height(16.dp))

                val listMark = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

                for (mark in listMark) {
                    val rank = filter(mark)
                    val textColor = getRankTextColor(rank)
                    val bgColor = getRankBgColor(rank)
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth()
                            , horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Điểm: ${mark}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "${rank}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(bgColor)
                                    .padding(horizontal = 8.dp , vertical = 1.dp)
                                )

                        }
                    }
                }

            }
            Button(
                onClick = {
                    (activity as Activity).finish()
                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
            ) {
                Text(text = "Back Home")
            }
        }
    }
}