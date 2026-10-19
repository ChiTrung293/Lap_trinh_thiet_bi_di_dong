package com.example.tuan3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ComponentItem(
    val name: String,
    val description: String
)

data class ComponentCategory(
    val title: String,
    val items: List<ComponentItem>
)

@Composable
fun UIComponentsListScreen(
    onBack: () -> Unit = {}
) {
    var selectedComponent by remember { mutableStateOf<String?>(null) }

    if (selectedComponent != null) {
        ComponentDetailScreen(
            componentName = selectedComponent!!,
            onBack = { selectedComponent = null }
        )
    } else {
        ComponentListView(
            onBack = onBack,
            onSelectComponent = { name -> selectedComponent = name }
        )
    }
}

@Composable
fun ComponentListView(
    onBack: () -> Unit,
    onSelectComponent: (String) -> Unit
) {
    val categories = listOf(
        ComponentCategory(
            title = "Display",
            items = listOf(
                ComponentItem("Text", "Displays text"),
                ComponentItem("Image", "Displays an image")
            )
        ),
        ComponentCategory(
            title = "Input",
            items = listOf(
                ComponentItem("TextField", "Input field for text"),
                ComponentItem("PasswordField", "Input field for passwords"),
                ComponentItem("Toggle", "On/off switch"),
                ComponentItem("Slider", "Selects a value from a range"),
                ComponentItem("DatePicker", "Selects a date and time")
            )
        ),
        ComponentCategory(
            title = "Selection",
            items = listOf(
                ComponentItem("Button", "Triggers an action when tapped"),
                ComponentItem("Checkbox", "Selects one or multiple options"),
                ComponentItem("RadioButton", "Selects a single option"),
                ComponentItem("Picker", "Selects a value from a list")
            )
        ),
        ComponentCategory(
            title = "Navigation",
            items = listOf(
                ComponentItem("NavigationView", "Navigation container with title and back button"),
                ComponentItem("TabBar", "Switches between main sections"),
                ComponentItem("Menu", "Shows a menu with multiple actions")
            )
        ),
        ComponentCategory(
            title = "Content",
            items = listOf(
                ComponentItem("List", "Displays a list of items"),
                ComponentItem("Card", "Container with content and style"),
                ComponentItem("Divider", "Separates content into sections")
            )
        ),
        ComponentCategory(
            title = "Layout",
            items = listOf(
                ComponentItem("Column", "Arranges elements vertically"),
                ComponentItem("Row", "Arranges elements horizontally")
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onBack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = "<",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF367DD2)
                )
            }

            Text(
                text = "UI Components List",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF367DD2)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            categories.forEach { category ->
                item {
                    Text(
                        text = category.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                items(category.items) { component ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable { onSelectComponent(component.name) },
                        shape = RoundedCornerShape(14.dp),
                        color = Color(0xFFCBE3FF)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
                        ) {
                            Text(
                                text = component.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = component.description,
                                fontSize = 14.sp,
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StyledTextSample(modifier: Modifier = Modifier) {
    val annotatedString = buildAnnotatedString {
        append("The ")

        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append("quick")
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFFC06800),
                fontSize = 32.sp
            )
        ) {
            append(" Brown")
        }

        append("\n")

        append("fox ")

        withStyle(style = SpanStyle(letterSpacing = 3.sp)) {
            append("jumps ")
        }

        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("over")
        }

        append("\n")

        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("the")
        }
        append(" ")

        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontSize = 22.sp
            )
        ) {
            append("lazy")
        }
        append(" ")

        append("dog.")
    }

    Text(
        text = annotatedString,
        fontSize = 22.sp,
        modifier = modifier
    )
}

@Composable
fun RowLayoutSample() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(4) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF4F6F9)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .background(Color(0xFFA1C4F7), shape = RoundedCornerShape(14.dp))
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .background(Color(0xFF3B82F6), shape = RoundedCornerShape(14.dp))
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .background(Color(0xFFA1C4F7), shape = RoundedCornerShape(14.dp))
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnLayoutSample() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) {
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF4F6F9)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color(0xFFA1C4F7), shape = RoundedCornerShape(14.dp))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color(0xFF3B82F6), shape = RoundedCornerShape(14.dp))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color(0xFFA1C4F7), shape = RoundedCornerShape(14.dp))
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(
    componentName: String,
    onBack: () -> Unit
) {
    val displayTitle = if (componentName == "Row" || componentName == "Column") "$componentName Layout" else componentName

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onBack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = "<",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF367DD2)
                )
            }

            Text(
                text = displayTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF367DD2)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFF5F9FF), shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            when (componentName) {
                "Text" -> {
                    StyledTextSample()
                }
                "Image" -> {
                    val imageUrl = "https://i.pinimg.com/originals/dd/67/2f/dd672f9e53a2e8b3716813cf7ea9d1b2.png"

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "1. Image from URL",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Online Image",
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = imageUrl,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "2. Local Image (img.png)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "Local Image img.png",
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                "TextField" -> {
                    var inputText by remember { mutableStateOf("") }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            label = { Text("Thông tin nhập") }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = inputText, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                }
                "PasswordField" -> {
                    var passwordText by remember { mutableStateOf("") }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = passwordText,
                            onValueChange = { passwordText = it },
                            label = { Text("Password") },
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                }
                "Column" -> {
                    ColumnLayoutSample()
                }
                "Row" -> {
                    RowLayoutSample()
                }
                "Button" -> {
                    var count by remember { mutableIntStateOf(0) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { count++ }) {
                            Text("Click Me ($count)")
                        }
                    }
                }
                "Checkbox" -> {
                    var checkedState by remember { mutableStateOf(true) }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = { checkedState = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (checkedState) "Checked" else "Unchecked")
                    }
                }
                "RadioButton" -> {
                    var selectedOption by remember { mutableStateOf("Option 1") }
                    Column {
                        listOf("Option 1", "Option 2", "Option 3").forEach { option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedOption = option }
                                    .padding(8.dp)
                            ) {
                                RadioButton(
                                    selected = (selectedOption == option),
                                    onClick = { selectedOption = option }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(option)
                            }
                        }
                    }
                }
                "Picker" -> {
                    var selectedItem by remember { mutableStateOf("Apple") }
                    var expanded by remember { mutableStateOf(false) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Selected Item: $selectedItem", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Box {
                            Button(onClick = { expanded = true }) {
                                Text("Choose Fruit")
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                listOf("Apple", "Banana", "Cherry", "Mango").forEach { fruit ->
                                    DropdownMenuItem(
                                        text = { Text(fruit) },
                                        onClick = {
                                            selectedItem = fruit
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                "Toggle" -> {
                    var switchState by remember { mutableStateOf(true) }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = switchState,
                            onCheckedChange = { switchState = it }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(if (switchState) "ON" else "OFF", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
                "Slider" -> {
                    var sliderPosition by remember { mutableFloatStateOf(0.5f) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Value: ${(sliderPosition * 100).toInt()}%")
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it }
                        )
                    }
                }
                "DatePicker" -> {
                    val datePickerState = rememberDatePickerState()
                    DatePicker(state = datePickerState)
                }
                "NavigationView" -> {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF367DD2),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("<", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Sample Navigation Header", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                "TabBar" -> {
                    var selectedTab by remember { mutableIntStateOf(0) }
                    val tabs = listOf("Home", "Search", "Settings")
                    Column {
                        TabRow(selectedTabIndex = selectedTab) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = selectedTab == index,
                                    onClick = { selectedTab = index },
                                    text = { Text(title) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Current Tab: ${tabs[selectedTab]}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                "Menu" -> {
                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        Button(onClick = { expanded = true }) {
                            Text("Open Menu")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Edit") },
                                onClick = { expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Share") },
                                onClick = { expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Delete") },
                                onClick = { expanded = false }
                            )
                        }
                    }
                }
                "List" -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("List Preview:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        repeat(4) { index ->
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFE3F2FD)
                            ) {
                                Text(
                                    text = "List Item #${index + 1}",
                                    modifier = Modifier.padding(12.dp),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                "Divider" -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Section 1")
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = Color(0xFF367DD2), thickness = 2.dp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Section 2")
                    }
                }
                else -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFCBE3FF)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text(
                                text = "Component: $componentName",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Interactive preview for $componentName component.")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
