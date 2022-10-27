package com.wutsi.membership.dto

import kotlin.Int

public data class SearchCategoryRequest(
    public val limit: Int = 100,
    public val offset: Int = 0
)
