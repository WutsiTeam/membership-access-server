package com.wutsi.membership.dto

import kotlin.collections.List

public data class SearchPlaceResponse(
    public val places: List<PlaceSummary> = emptyList()
)
