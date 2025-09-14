package com.kabindra.musicgpt.utils.enums

enum class CreateSongType(val title: String, val slug: String) {
    Queue("Queue", "queue"),
    Generate("Generate", "generate"),
}

inline fun <reified T : Enum<T>> getCreateSongType(slug: String): CreateSongType {
    return enumValues<T>().find { (it as CreateSongType).slug == slug } as CreateSongType
}