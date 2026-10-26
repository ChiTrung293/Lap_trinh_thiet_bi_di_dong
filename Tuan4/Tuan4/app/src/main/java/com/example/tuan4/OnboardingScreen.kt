package com.example.tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.tuan4.ui.theme.Tuan4Theme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            title = "Mọi việc, gọn trong một chỗ",
            description = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
            imageType = ImageType.CHECKLIST
        ),
        OnboardingPage(
            title = "Chia theo Học tập và Cuộc sống",
            description = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
            imageType = ImageType.CATEGORIES
        ),
        OnboardingPage(
            title = "Nhắc đúng lúc",
            description = "Bật thông báo để UTH Todo nhắc bạn trước hạn chót.",
            imageType = ImageType.REMINDER
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    val primaryBlue = Color(0xFF3855E0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LogoIcon(size = 24, iconColor = Color.White, backgroundColor = primaryBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "UTH Todo",
                    color = primaryBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "Bỏ qua",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onFinish() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { position ->
            val page = pages[position]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Image Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    when (page.imageType) {
                        ImageType.CHECKLIST -> ChecklistGraphic()
                        ImageType.CATEGORIES -> CategoriesGraphic()
                        ImageType.REMINDER -> ReminderGraphic()
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))

                // Text
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = page.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = page.description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Bottom section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Dots indicator
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(pages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) primaryBlue else Color.LightGray
                    val width = if (pagerState.currentPage == iteration) 24.dp else 8.dp
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(width)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Button
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) "Bắt đầu ngay" else "Tiếp tục",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ChecklistGraphic() {
    val primaryBlue = Color(0xFF3855E0)
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .width(200.dp)
            .background(Color(0xFFF5F7FF), RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        // Item 1 (Checked)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(primaryBlue)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(primaryBlue)
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(6.dp).clip(CircleShape).background(Color.White))
            }
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
        
        // Item 2 (Unchecked)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(1.dp).clip(CircleShape).background(Color.White))
            }
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.8f) // Shorter line
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
        
        // Item 3 (Unchecked)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(1.dp).clip(CircleShape).background(Color.White))
            }
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
    }
}

@Composable
fun CategoriesGraphic() {
    val primaryBlue = Color(0xFF3855E0)
    val orange = Color(0xFFFF9800)
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .width(200.dp)
            .background(Color(0xFFF5F7FF), RoundedCornerShape(20.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Học tập", 
                color = primaryBlue, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(primaryBlue.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Cuộc sống", 
                color = orange, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(orange.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesGraphicPreview() {
    Tuan4Theme {
        CategoriesGraphic()
    }
}

@Composable
fun ReminderGraphic() {
    val primaryBlue = Color(0xFF3855E0)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(200.dp)
            .background(Color(0xFFF5F7FF), RoundedCornerShape(16.dp))
    ) {
        // Bell placeholder
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 4.dp, bottomEnd = 4.dp))
                    .background(primaryBlue)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(primaryBlue)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ôn bài Toán lúc 19:00",
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChecklistGraphicPreview() {
    Tuan4Theme {
        ChecklistGraphic()
    }
}
