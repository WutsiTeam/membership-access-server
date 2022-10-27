package com.wutsi.membership.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.String

public data class SearchPlaceRequest(
    @get:Size(max = 50)
    public val keyword: String? = null,
    @get:NotBlank
    public val type: String = "",
    @get:NotBlank
    public val country: String = ""
)
