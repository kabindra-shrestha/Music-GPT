package com.kabindra.musicgpt.utils.base

import kotlinx.serialization.Serializable

@Serializable
data class DashboardRefreshEvent(
    var refresh: Boolean
)