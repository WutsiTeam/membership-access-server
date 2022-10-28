package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.CategorySummary
import com.wutsi.membership.dto.SearchCategoryRequest
import com.wutsi.membership.dto.SearchCategoryResponse
import com.wutsi.membership.service.CategoryService
import com.wutsi.platform.core.logging.KVLogger
import org.springframework.stereotype.Service
import java.lang.Integer.min
import javax.servlet.http.HttpServletRequest

@Service
class SearchCategoryDelegate(
    private val service: CategoryService,
    private val httpRequest: HttpServletRequest,
    private val logger: KVLogger
) {
    fun invoke(request: SearchCategoryRequest): SearchCategoryResponse {
        logger.add("request_limit", request.limit)
        logger.add("request_offset", request.offset)

        val language = httpRequest.getHeader("Accept-Language")
        logger.add("language", language)

        val categories = service.findAll()
        logger.add("count", categories.size)

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
