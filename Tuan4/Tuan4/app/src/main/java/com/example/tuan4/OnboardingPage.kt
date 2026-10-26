package com.example.tuan4

import androidx.annotation.DrawableRes

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageType: ImageType
)

enum class ImageType {
    CHECKLIST,
    CATEGORIES,
    REMINDER
}
