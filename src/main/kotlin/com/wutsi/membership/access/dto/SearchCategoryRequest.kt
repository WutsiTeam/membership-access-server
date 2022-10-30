package com.wutsi.membership.access.dto

import kotlin.Int
import kotlin.String

public data class SearchCategoryRequest(
    public val keyword: String? = null,
    public val limit: Int = 100,
    public val offset: Int = 0
)
