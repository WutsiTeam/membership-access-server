package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.SearchPlaceDelegate
import com.wutsi.membership.dto.SearchPlaceRequest
import com.wutsi.membership.dto.SearchPlaceResponse
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RequestBody
import org.springframework.web.bind.`annotation`.RestController
import javax.validation.Valid

@RestController
public class SearchPlaceController(
    public val `delegate`: SearchPlaceDelegate
) {
    @PostMapping("/v1/places/search")
    public fun invoke(
        @Valid @RequestBody
        request: SearchPlaceRequest
    ): SearchPlaceResponse =
        delegate.invoke(request)
}
