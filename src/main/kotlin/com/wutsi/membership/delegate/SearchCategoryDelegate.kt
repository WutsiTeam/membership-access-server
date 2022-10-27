package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.CategorySummary
import com.wutsi.membership.dto.SearchCategoryRequest
import com.wutsi.membership.dto.SearchCategoryResponse
import com.wutsi.membership.service.CategoryService
import org.springframework.stereotype.Service
import java.lang.Integer.min
import javax.servlet.http.HttpServletRequest

@Service
class SearchCategoryDelegate(
    private val service: CategoryService,
    private val httpRequest: HttpServletRequest
) {
    fun invoke(request: SearchCategoryRequest): SearchCategoryResponse {
        val language = httpRequest.getHeader("Accept-Language")
        val categories = service.findAll()
        return if (request.offset >= categories.size) {
            SearchCategoryResponse()
        } else {
            SearchCategoryResponse(
                categories = categories.map {
                    CategorySummary(
                        id = it.id ?: -1,
                        title = service.getTitle(it, language)
                    )
                }.sortedBy { it.title }
                    .subList(
                        request.offset,
                        min(request.offset + request.limit, categories.size)
                    )
            )
        }
    }
}
