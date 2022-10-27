package com.wutsi.membership.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_PLACE")
data class PlaceEntity(
    @Id
    val id: Long = -1,
    val name: String = "",
    val nameFrench: String = "",
    val country: String = "",
    val longitude: Double? = null,
    val latitude: Double? = null,
    val timezoneId: String? = null,
    val type: PlaceType = PlaceType.UNKNOWN
)
