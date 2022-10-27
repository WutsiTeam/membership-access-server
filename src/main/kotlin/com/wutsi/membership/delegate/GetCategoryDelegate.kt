package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.Category
import com.wutsi.membership.dto.GetCategoryResponse
import com.wutsi.membership.service.CategoryService
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class GetCategoryDelegate(
    private val service: CategoryService,
    private val request: HttpServletRequest
) {
    fun invoke(id: Long): GetCategoryResponse {
        val category = service.findById(id)
        val language = request.getHeader("Accept-Language")
        return GetCategoryResponse(
            category = Category(
                id = category.id ?: -1,
                title = service.getTitle(category, language)
            )
        )
    }
}
