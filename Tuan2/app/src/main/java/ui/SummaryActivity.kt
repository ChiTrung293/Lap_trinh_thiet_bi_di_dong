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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoresheetw2.ui.theme.ScoreSheetW2Theme


class SummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreSheetW2Theme {
                SummaryActivityScreen()
                HeaderTitleBox("Summary Activity")
            }
        }
    }

    class Student(
        val name: String,
        val Score: Double? = null,
    )

    fun calculateAverage(students: List<Student>): Double {
        var totalScore = 0.0
        var count = 0
        for (Student in students) {
            if (Student.Score != null) {
                totalScore += Student.Score
                count++
            }
        }
        return if (count > 0) totalScore / count else 0.0
    }

    fun CountGoodRank(students: List<Student>): Int {
        var count = 0
        for (student in students) {
            if (student.Score != null && student.Score >= 8.0) {
            count++
        }
        }
            return count
    }

    fun unknowScore(students: List<Student>): Int {
        var count = 0
        for (student in students) {
            if (student.Score == null) {
                count++
            }
        }
        return count
    }

    fun GetTopStudent(students: List<Student>): Student? {
        return students.maxByOrNull { it.Score ?: 0.0 }
    }

    fun filter(
        Score: Double?
    ): String {
        if (Score == null) {
            return "Chưa có điểm"
        }
        if (Score < 0.0 || Score > 10.0) {
            return "Không hợp lệ"
        }
        if (Score >= 8.0) {
            return "Giỏi"
        }
        if (Score >= 6.5) {
            return "Khá"
        }
        if (Score >= 5.0) {
            return "Trung bình"
        }
        if (Score < 5.0) {
            return "Yếu"
        }

        else return "Chưa có điểm"
    }
    fun getRankTextColor(rank: String): Color {
        if (rank == "Giỏi") {
            return Color(0xFF4CAF50)
        } else if (rank == "Khá") {
            return Color(0xFF1565C0)
        } else if (rank == "Trung bình") {
            return Color(0xFF884F2F)
        } else if (rank == "Yếu") {
            return Color(0xFFC62828)
        }
        else if(rank == "Chưa có điểm") {
            return Color.Gray
        }
        else {
            return Color.Gray
        }
    }
    val StudentList = listOf(
        Student(
            name = "Nguyễn Văn An",
            Score = 9.0
        ),
        Student(
            name = "Trần Thị Bình",
            Score = 7.2
        ),
        Student(
            name = "Lê Minh Chi",
        ),
        Student(
            name = "Phạm Quốc Dũng",
            Score = 4.5
        ),
        Student(
            name = "Võ Thanh Em",
            Score = 5.8
        )
    )
    @Composable
    fun HeaderTitleBox(title: String) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFF263685))
        ) {
            Text(
                text = "Summary Activity",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp)
            )
            Text(
                text = "Bảng điểm lớp",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 70.dp)
            )
        }
    }

    @Composable
    fun SummaryActivityScreen() {
        val activity = LocalContext.current
        val average = calculateAverage(StudentList)
        val topStudent = GetTopStudent(StudentList)
        val unknowScore = unknowScore(StudentList)
        Box(modifier = Modifier.fillMaxSize().padding(top= 120.dp),
            contentAlignment = Alignment.Center)
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
            ){

                Card(
                modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(
                        0xDA0B2C6E
                    )
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(1f),
                    ) {
                        Text(
                            text = "Điểm trung bình lớp",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = String.format("%.2f", average),
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 16.dp),
                        )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Giỏi",
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "${CountGoodRank(StudentList)}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )


                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Chưa có điểm",
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "${unknowScore}",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )


                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Cao nhất",
                                        fontSize = 16.sp,
                                        color = Color.Gray

                                    )
                                    Text(
                                        text = "${topStudent?.Score}",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )


                                }
                            }


                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "DANH SÁCH • ${StudentList.count()} SINH VIÊN"
                )

                Spacer(modifier = Modifier.height(16.dp))



                for (student in StudentList) {
                    val rank = filter(student.Score)
                    val textColor = getRankTextColor(rank)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${student.name}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = "${rank}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(textColor.value)
                                )

                            }
                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "${student.Score?:"——"}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = Color.Black
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
