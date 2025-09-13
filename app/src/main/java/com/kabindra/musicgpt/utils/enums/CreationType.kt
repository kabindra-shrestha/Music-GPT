package com.kabindra.musicgpt.utils.enums

enum class CreationType(val title: String, val slug: String) {
    Generating("Generating", "generating"),
    Generate0("Generate0", "generate0"),
    Generate25("Generate25", "generate25"),
    Generate50("Generate50", "generate50"),
    Generate75("Generate75", "generate75"),
    Generate90("Generate90", "generate90"),
    Generate100("Generate100", "generate100"),
    Queue("Queue", "queue"),
    Generated("Generated", "generated"),
}

inline fun <reified T : Enum<T>> getCreationType(slug: String): CreationType {
    return enumValues<T>().find { (it as CreationType).slug == slug } as CreationType
}