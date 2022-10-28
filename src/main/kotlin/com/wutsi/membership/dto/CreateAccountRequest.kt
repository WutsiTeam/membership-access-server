package com.wutsi.membership.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.Long
import kotlin.String

public data class CreateAccountRequest(
    @get:NotBlank
    public val phoneNumber: String = "",
    @get:NotBlank
    @get:Size(max = 2)
    public val language: String = "",
    @get:NotBlank
    @get:Size(max = 2)
    public val country: String = "",
    @get:NotBlank
    @get:Size(max = 50)
    public val displayName: String = "",
    public val pictureUrl: String? = null,
    @get:Min(1)
    public val cityId: Long = 0
)
