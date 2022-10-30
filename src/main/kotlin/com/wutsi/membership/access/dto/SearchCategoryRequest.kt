package com.wutsi.membership.access.dto

public data class SearchCategoryRequest(
    public val categoryIds: List<Long> = emptyList(),
    public val keyword: String? = null,
    public val limit: Int = 100,
    public val offset: Int = 0
)
