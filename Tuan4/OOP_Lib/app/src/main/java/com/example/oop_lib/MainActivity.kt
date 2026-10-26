package com.example.oop_lib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oop_lib.ui.theme.OOP_LibTheme

// --- OOP PRINCIPLES DEMONSTRATION ---

// 1. Abstraction (Trừu tượng): Lớp trừu tượng định nghĩa khung cho các đối tượng trong thư viện
abstract class LibraryEntity {
    abstract fun getDetails(): String
}

// 2. Inheritance (Kế thừa): Lớp Student kế thừa từ LibraryEntity
class Student(private var _name: String) : LibraryEntity() {
    
    // 3. Encapsulation (Đóng gói): Các thuộc tính được ẩn (private) và truy cập qua getter/setter
    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    // 4. Polymorphism (Đa hình): Ghi đè phương thức từ lớp cha
    override fun getDetails(): String {
        return "Sinh viên: $_name"
    }
}

// Lớp đại diện cho Sách, cũng kế thừa từ LibraryEntity
class Book(private var _title: String, private var _isSelected: Boolean = true) : LibraryEntity() {
    
    var title: String
        get() = _title
        set(value) { _title = value }
        
    var isSelected: Boolean
        get() = _isSelected
        set(value) { _isSelected = value }

    override fun getDetails(): String {
        return "Sách: $_title (Chọn: $_isSelected)"
    }
}

// --- END OOP PRINCIPLES ---

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOP_LibTheme {
                LibraryAppScreen()
            }
        }
    }
}

@Composable
fun LibraryAppScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    var student by remember { mutableStateOf(Student("Nguyen Van A")) }
    var bookList by remember {
        mutableStateOf(
            listOf(
                Book("Sách 01", true),
                Book("Sách 02", true)
            )
        )
    }
    
    // Dữ liệu mẫu cho các tab khác
    val allBooks = remember { 
        listOf(
            Book("Sách 01", false), 
            Book("Sách 02", false), 
            Book("Sách Lập trình Android", false), 
            Book("Sách OOP Cơ bản", false)
        ) 
    }
    
    val allStudents = remember { 
        listOf(
            Student("Nguyen Van A"), 
            Student("Tran Thi B"), 
            Student("Le Van C"), 
            Student("Pham Thi D")
        ) 
    }

    LaunchedEffect(student, bookList) {
        println(student.getDetails())
        bookList.forEach { println(it.getDetails()) }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Text("🏠") },
                    label = { Text("Quản lý") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF3F51B5),
                        selectedTextColor = Color(0xFF3F51B5),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    icon = { Text("📄") },
                    label = { Text("DS Sách") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF3F51B5),
                        selectedTextColor = Color(0xFF3F51B5),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    icon = { Text("👤") },
                    label = { Text("Sinh viên") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF3F51B5),
                        selectedTextColor = Color(0xFF3F51B5),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> ManageScreen(
                innerPadding = innerPadding,
                student = student,
                onStudentChange = { student = it },
                bookList = bookList,
                onBookCheckedChange = { index, isChecked ->
                    val newList = bookList.toMutableList()
                    newList[index] = Book(bookList[index].title, isChecked)
                    bookList = newList
                }
            )
            1 -> ListScreen(innerPadding, "Danh sách Sách", allBooks)
            2 -> ListScreen(innerPadding, "Danh sách Sinh viên", allStudents)
        }
    }
}

@Composable
fun ManageScreen(
    innerPadding: PaddingValues,
    student: Student,
    onStudentChange: (Student) -> Unit,
    bookList: List<Book>,
    onBookCheckedChange: (Int, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sinh viên",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = student.name,
                onValueChange = { onStudentChange(Student(it)) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* Change Action */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Danh sách sách",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                bookList.forEachIndexed { index, book ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = book.isSelected,
                            onCheckedChange = { isChecked ->
                                onBookCheckedChange(index, isChecked)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFC62828),
                                checkmarkColor = Color.White
                            )
                        )
                        Text(text = book.title, color = Color.DarkGray, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Add Action */ },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2855B5))
        ) {
            Text("Thêm", modifier = Modifier.padding(vertical = 4.dp), fontSize = 16.sp)
        }
    }
}

@Composable
fun ListScreen(
    innerPadding: PaddingValues,
    title: String,
    items: List<LibraryEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items.forEach { entity ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        // Tính đa hình (Polymorphism) được thể hiện ở đây
                        // Hàm getDetails() sẽ trả về text khác nhau tuỳ vào đối tượng là Student hay Book
                        Text(
                            text = entity.getDetails(), 
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}
