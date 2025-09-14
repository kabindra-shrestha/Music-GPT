package com.kabindra.musicgpt.utils

import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.utils.enums.CreationType

fun mapToCheckpoint(progress: Int): Int {
    return when {
        progress >= 100 -> 100
        progress >= 90 -> 90
        progress >= 75 -> 75
        progress >= 50 -> 50
        progress >= 25 -> 25
        else -> 0
    }
}

fun mapToCreationImage(progress: Int): Int {
    return when (progress) {
        0 -> R.drawable.property0
        25 -> R.drawable.property25
        50 -> R.drawable.property50
        75 -> R.drawable.property75
        90 -> R.drawable.property90
        100 -> R.drawable.property100
        -1 -> R.drawable.propertyfinish
        else -> R.drawable.property0
    }
}

fun mapToCreationType(progress: Int): String {
    return when (progress) {
        0 -> CreationType.Generate0.slug
        25 -> CreationType.Generate25.slug
        50 -> CreationType.Generate50.slug
        75 -> CreationType.Generate75.slug
        90 -> CreationType.Generate90.slug
        100 -> CreationType.Generate100.slug
        -1 -> CreationType.Generated.slug
        else -> CreationType.Generate0.slug
    }
}