package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.UpdateAccountAttributeDelegate
import com.wutsi.membership.dto.UpdateAccountAttributeRequest
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RequestBody
import org.springframework.web.bind.`annotation`.RestController
import javax.validation.Valid
import kotlin.Long

@RestController
public class UpdateAccountAttributeController(
    public val `delegate`: UpdateAccountAttributeDelegate
) {
    @PostMapping("/v1/accounts/{id}/attributes")
    public fun invoke(
        @PathVariable(name = "id") id: Long,
        @Valid @RequestBody
        request: UpdateAccountAttributeRequest
    ) {
        delegate.invoke(id, request)
    }
}
