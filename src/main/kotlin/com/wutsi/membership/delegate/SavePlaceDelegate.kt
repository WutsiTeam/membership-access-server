package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.SavePlaceRequest
import com.wutsi.membership.service.PlaceService
import org.springframework.stereotype.Service

@Service
public class SavePlaceDelegate(private val service: PlaceService) {
    public fun invoke(request: SavePlaceRequest) {
        service.save(request)
    }
}
