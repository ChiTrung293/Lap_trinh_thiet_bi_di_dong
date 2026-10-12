package ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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


class Exercise2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreSheetW2Theme {
                HeaderTitleBox("Exercise 2")
                Exercise2Screen()
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
                text = "Exercise 2",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp)
            )
            Text(
                text = "Danh sách sinh viên",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 70.dp)
            )
        }
    }

    @Composable
    fun Exercise2Screen() {

        class Student(
            val name: String,
            val email: String? = null,
            val phone: String? = null,
        )

        val activity = LocalContext.current
        val studentList = listOf(
            Student(
                name = "Nguyễn Văn An",
                email = "an@sv.com",
                phone = "0901234567"
            ),
            Student(
                name = "Nguyễn Văn Bình",
                email = "binh@sv.com"
            ),
            Student(
                name = "Nguyễn Văn Cường",
                phone = "0901234569"
            ),
            Student(
                name = "Nguyễn Văn Dũng",
            ),
            Student(
                name = "Nguyễn Văn Em",
                email = "em@sv.com",
                phone = "0901234561"
            )
        )


        Box(
            modifier = Modifier.fillMaxSize().padding(top = 120.dp),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for (student in studentList) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${student.name}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row{
                                Text(
                                    text = "Email: "
                                )
                                Text(
                                    text = "${student.email?: "Chưa cập nhật"}",
                                    fontSize = 16.sp,
                                    color = if (student.email != null) Color.Black else Color.Red
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Row{
                                Text(
                                    text = "SĐT: "
                                )
                                Text(
                                    text = "${student.phone?: "Chưa cập nhật"}",
                                    fontSize = 16.sp,
                                    color = if (student.email != null) Color.Black else Color.Red
                                )
                            }


                        }
                    }


                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Row (
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Tổng: "
                        )
                        Text(
                            text = "${studentList.size} sinh viên",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "Thiếu email: ",
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "${studentList.count { it.email == null }}",
                                    color = Color.Red

                        )
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