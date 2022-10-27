package com.wutsi.membership.dto

import kotlin.collections.List

public data class ListCategoryResponse(
    public val categories: List<CategorySummary> = emptyList()
)
