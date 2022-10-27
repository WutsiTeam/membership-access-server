package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.GetPlaceDelegate
import com.wutsi.membership.dto.GetPlaceResponse
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.RestController
import kotlin.Long

@RestController
public class GetPlaceController(
    public val `delegate`: GetPlaceDelegate
) {
    @GetMapping("/v1/places/{id}")
    public fun invoke(@PathVariable(name = "id") id: Long): GetPlaceResponse = delegate.invoke(id)
}
