package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.SearchPlaceRequest
import com.wutsi.membership.dto.SearchPlaceResponse
import com.wutsi.membership.service.PlaceService
import com.wutsi.platform.core.logging.KVLogger
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
public class SearchPlaceDelegate(
    private val service: PlaceService,
    private val httpRequest: HttpServletRequest,
    private val logger: KVLogger
) {
    public fun invoke(request: SearchPlaceRequest): SearchPlaceResponse {
        logger.add("request_country", request.country)
        logger.add("request_keyboard", request.keyword)
        logger.add("request_type", request.type)
        logger.add("request_limit", request.limit)
        logger.add("request_offset", request.offset)

        val language = httpRequest.getHeader("Accept-Language")
        logger.add("language", language)

        val places = service.search(request)
        logger.add("count", places.size)

        return SearchPlaceResponse(
            places = places.map {
                service.toPlaceSummary(it, language)
            }
        )
    }
}
