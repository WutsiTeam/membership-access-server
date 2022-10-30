package com.wutsi.membership.access.delegate

import com.wutsi.membership.access.dto.SearchCategoryRequest
import com.wutsi.membership.access.dto.SearchCategoryResponse
import com.wutsi.membership.access.service.CategoryService
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
                categories = categories.map { service.toCategorySummary(it, language) }
                    .sortedBy { it.title }
                    .subList(
                        request.offset,
                        min(request.offset + request.limit, categories.size)
                    )
            )
        }
    }
}