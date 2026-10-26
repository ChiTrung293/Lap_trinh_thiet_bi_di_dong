package com.example.tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
    
    val primaryBlue = Color(0xFF3855E0)
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryBlue)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoIcon(size = 80, iconColor = primaryBlue, backgroundColor = Color.White)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "UTH Todo",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Việc học, việc nhà – gọn gàng mỗi ngày",
                color = Color.White,
                fontSize = 14.sp
            )
        }
        
        Text(
            text = "Phiên bản 2.1.0",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}

@Composable
fun LogoIcon(size: Int, iconColor: Color, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(RoundedCornerShape((size / 4).dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy((size / 8).dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy((size / 10).dp)
            ) {
                Box(
                    modifier = Modifier
                        .size((size / 6).dp)
                        .clip(CircleShape)
                        .background(iconColor)
                ) {
                    // Inner check mark can be drawn here if needed, but a circle is fine for simplicity
                    Box(modifier = Modifier.fillMaxSize().padding((size/30).dp).clip(CircleShape).background(backgroundColor))
                    Box(modifier = Modifier.size((size/12).dp).align(Alignment.Center).clip(CircleShape).background(iconColor))
                }
                Box(
                    modifier = Modifier
                        .height((size / 8).dp)
                        .width((size / 2.5).dp)
                        .clip(RoundedCornerShape(50))
                        .background(iconColor)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy((size / 10).dp)
            ) {
                Box(
                    modifier = Modifier
                        .size((size / 6).dp)
                        .clip(CircleShape)
                        .background(iconColor.copy(alpha = 0.5f))
                )
                Box(
                    modifier = Modifier
                        .height((size / 8).dp)
                        .width((size / 3.5).dp)
                        .clip(RoundedCornerShape(50))
                        .background(iconColor.copy(alpha = 0.5f))
                )
            }
        }
    }
}
