package com.wutsi.membership.dto

public data class SearchPlaceResponse(
    public val places: List<PlaceSummary> = emptyList()
)
