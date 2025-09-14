package com.kabindra.musicgpt.utils.ktor

class UserAgentInterceptor(private val headersProvider: () -> Map<String, String>)
