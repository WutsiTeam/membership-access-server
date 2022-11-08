package com.wutsi.membership.access.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime

public data class AccountSummary(
    public val id: Long = 0,
    public val pictureUrl: String? = null,
    public val status: String = "",
    public val displayName: String = "",
    public val language: String = "",
    public val country: String = "",
    @get:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    public val created: OffsetDateTime = OffsetDateTime.now(),
    @get:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    public val updated: OffsetDateTime = OffsetDateTime.now(),
    public val superUser: Boolean = false,
    public val business: Boolean = false,
    public val cityId: Long? = null,
    public val categoryId: Long? = null,
    public val storeId: Long? = null,
    public val walletId: Long? = null
)
