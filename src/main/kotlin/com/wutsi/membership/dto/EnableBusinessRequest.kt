package com.wutsi.membership.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

public data class EnableBusinessRequest(
    @get:NotBlank
    @get:Size(max = 50)
    public val displayName: String = "",
    @get:NotNull
    public val categoryId: Long? = null,
    @get:NotBlank
    public val country: String = "",
    public val cityId: Long = -1,
    public val street: String? = null,
    public val biography: String? = null,
    public val whatsapp: String? = null
)
