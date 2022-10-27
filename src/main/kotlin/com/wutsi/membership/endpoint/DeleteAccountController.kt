package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.DeleteAccountDelegate
import org.springframework.web.bind.`annotation`.DeleteMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.RestController
import kotlin.Long

@RestController
public class DeleteAccountController(
    public val `delegate`: DeleteAccountDelegate
) {
    @DeleteMapping("/v1/accounts/{id}")
    public fun invoke(@PathVariable(name = "id") id: Long) {
        delegate.invoke(id)
    }
}
