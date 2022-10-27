package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.UpdateAccountDelegate
import com.wutsi.membership.dto.UpdateAccountRequest
import com.wutsi.membership.dto.UpdateAccountResponse
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RequestBody
import org.springframework.web.bind.`annotation`.RestController
import javax.validation.Valid
import kotlin.Long

@RestController
public class UpdateAccountController(
    public val `delegate`: UpdateAccountDelegate
) {
    @PostMapping("/v1/accounts/{id}")
    public fun invoke(
        @PathVariable(name = "id") id: Long,
        @Valid @RequestBody
        request: UpdateAccountRequest
    ): UpdateAccountResponse = delegate.invoke(id, request)
}
