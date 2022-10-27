package com.wutsi.membership.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_CATEGORY")
data class CategoryEntity(
    @Id
    val id: Long = -1,
    val title: String = "",
    val titleFrench: String = ""
)
