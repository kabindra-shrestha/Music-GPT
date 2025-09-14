package com.kabindra.musicgpt.utils.enums

import com.kabindra.musicgpt.R

enum class ActionType(val title: String, val slug: String, val icon: Int) {
    V1("V1", "v1", R.drawable.v1),
    Option("Option", "option", R.drawable.frame_1261154109),
}

inline fun <reified T : Enum<T>> getActionType(slug: String): ActionType {
    return enumValues<T>().find { (it as ActionType).slug == slug } as ActionType
}