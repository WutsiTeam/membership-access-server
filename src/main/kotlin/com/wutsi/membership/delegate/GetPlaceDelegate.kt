package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.GetPlaceResponse
import com.wutsi.membership.service.PlaceService
import com.wutsi.platform.core.logging.KVLogger
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
public class GetPlaceDelegate(
    private val service: PlaceService,
    private val request: HttpServletRequest,
    private val logger: KVLogger
) {
    public fun invoke(id: Long): GetPlaceResponse {
        val language = request.getHeader("Accept-Language")
        logger.add("language", language)

        val place = service.findById(id)
        return GetPlaceResponse(
            place = service.toPlace(place, language)
        )
    }
}
