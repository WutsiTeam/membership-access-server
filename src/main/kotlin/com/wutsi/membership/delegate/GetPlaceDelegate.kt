package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.GetPlaceResponse
import com.wutsi.membership.dto.Place
import com.wutsi.membership.service.PlaceService
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
public class GetPlaceDelegate(
    private val service: PlaceService,
    private val request: HttpServletRequest
) {
    public fun invoke(id: Long): GetPlaceResponse {
        val place = service.findById(id)
        val language = request.getHeader("Accept-Language")
        return GetPlaceResponse(
            place = Place(
                id = place.id,
                name = service.getName(place, language),
                longName = service.getLongName(place, language),
                latitude = place.latitude,
                longitude = place.longitude,
                country = place.country,
                type = place.type.name,
                timezoneId = place.timezoneId
            )
        )
    }
}
