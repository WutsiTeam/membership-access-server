package com.wutsi.membership.dto

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class Place(
    public val id: Long = 0,
    public val name: String = "",
    public val country: String = "",
    public val type: String = "",
    public val longitude: Double = 0.0,
    public val latitude: Double = 0.0,
    public val timezoneId: String = ""
)
