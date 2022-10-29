package com.wutsi.membership.access.service

import com.wutsi.membership.access.dao.CategoryRepository
import com.wutsi.membership.access.dto.Category
import com.wutsi.membership.access.dto.CategorySummary
import com.wutsi.membership.access.entity.CategoryEntity
import com.wutsi.membership.access.error.ErrorURN
import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType
import com.wutsi.platform.core.error.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class CategoryService(private val dao: CategoryRepository) {
    fun findById(id: Long): CategoryEntity =
        dao.findById(id).orElseThrow {
            NotFoundException(
                error = Error(
                    code = ErrorURN.CATEGORY_NOT_FOUND.urn,
                    parameter = Parameter(
                        name = "id",
                        value = id,
                        type = ParameterType.PARAMETER_TYPE_PATH
                    )
                )
            )
        }

    fun findAll(): List<CategoryEntity> =
        dao.findAll()
            .toList()

    fun getTitle(category: CategoryEntity, language: String?) =
        when (language?.lowercase()) {
            "fr" -> category.titleFrench
            else -> category.title
        }

    fun toCategory(category: CategoryEntity, language: String?) = Category(
        id = category.id,
        title = getTitle(category, language)
    )

    fun toCategorySummary(category: CategoryEntity, language: String?) = CategorySummary(
        id = category.id,
        title = getTitle(category, language)
    )
}
