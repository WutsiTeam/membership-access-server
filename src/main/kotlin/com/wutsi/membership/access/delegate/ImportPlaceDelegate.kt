package com.wutsi.membership.access.delegate

import com.wutsi.membership.access.service.PlaceService
import org.springframework.stereotype.Service

@Service
public class ImportPlaceDelegate(private val service: PlaceService) {
    public fun invoke(country: String) {
        service.import(country)
    }
}
