package com.kabindra.musicgpt.data.source.remote

import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.data.model.MusicDTO
import com.kabindra.musicgpt.utils.enums.ActionType
import com.kabindra.musicgpt.utils.enums.CreationType

class ApiDataSource() {

    suspend fun getHome(): List<MusicDTO> {
        return getMockMusics()
    }

    suspend fun queueSong(): MusicDTO {
        return queueMockSong()
    }

    suspend fun generateSong(): MusicDTO {
        return generateMockSong()
    }

}

fun getMockMusics(): List<MusicDTO> {
    return listOf(
        MusicDTO(
            3,
            "Language Training",
            "Create a presentation that explains how large language models are used in the real world.",
            R.drawable.voice_pic1,
            ActionType.Option.slug,
            false,
            CreationType.Generated.slug
        ),
        MusicDTO(
            4,
            "Bam Bam",
            "Generate a script for a play about the power of forgiveness.",
            R.drawable.voice_pic2,
            ActionType.Option.slug,
            false,
            CreationType.Generated.slug
        ),
        MusicDTO(
            5,
            "Enemy",
            "Compose a poem about the meaning of life.",
            R.drawable.voice_pic3,
            ActionType.Option.slug,
            true,
            CreationType.Generated.slug
        ),
        MusicDTO(
            6,
            "Balenciaga",
            "Generate a poem about a lost love.",
            R.drawable.voice_pic4,
            ActionType.Option.slug,
            false,
            CreationType.Generated.slug
        ),
    )
}

fun queueMockSong(): MusicDTO {
    return MusicDTO(
        2,
        "Create a funky house song with female vocals",
        "21.4K users in queue skip",
        R.drawable.property0,
        ActionType.V1.slug,
        false,
        CreationType.Queue.slug
    )
}

fun generateMockSong(): MusicDTO {
    return MusicDTO(
        1,
        "Create a funky house song with female vocals",
        "Starting AI audio engine",
        R.drawable.property0,
        ActionType.V1.slug,
        false,
        CreationType.Generating.slug
    )
}