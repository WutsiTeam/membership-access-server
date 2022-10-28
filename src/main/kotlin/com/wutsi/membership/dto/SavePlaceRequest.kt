package com.wutsi.membership.dto

import javax.validation.constraints.NotBlank
import kotlin.Double
import kotlin.Long
import kotlin.String

public data class SavePlaceRequest(
    public val id: Long = 0,
    @get:NotBlank
    public val name: String = "",
    @get:NotBlank
    public val nameFrench: String = "",
    @get:NotBlank
    public val country: String = "",
    @get:NotBlank
    public val type: String = "",
    public val longitude: Double = 0.0,
    public val latitude: Double = 0.0,
    public val timezoneId: String = ""
)
