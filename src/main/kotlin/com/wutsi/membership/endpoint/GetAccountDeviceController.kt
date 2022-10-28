package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.GetAccountDeviceDelegate
import com.wutsi.membership.dto.GetAccountDeviceResponse
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.RestController
import kotlin.Long

@RestController
public class GetAccountDeviceController(
    public val `delegate`: GetAccountDeviceDelegate
) {
    @GetMapping("/v1/accounts/{id}/device")
    public fun invoke(@PathVariable(name = "id") id: Long): GetAccountDeviceResponse =
        delegate.invoke(id)
}
