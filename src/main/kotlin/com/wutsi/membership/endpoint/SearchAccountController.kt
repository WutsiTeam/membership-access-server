package com.wutsi.membership.endpoint

import com.wutsi.membership.`delegate`.SearchAccountDelegate
import com.wutsi.membership.dto.SearchAccountRequest
import com.wutsi.membership.dto.SearchAccountResponse
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RequestBody
import org.springframework.web.bind.`annotation`.RestController
import javax.validation.Valid

@RestController
public class SearchAccountController(
    public val `delegate`: SearchAccountDelegate
) {
    @PostMapping("/v1/accounts/search")
    public fun invoke(
        @Valid @RequestBody
        request: SearchAccountRequest
    ): SearchAccountResponse =
        delegate.invoke(request)
}
