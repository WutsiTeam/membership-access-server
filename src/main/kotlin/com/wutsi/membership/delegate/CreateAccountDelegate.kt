package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.CreateAccountRequest
import com.wutsi.membership.dto.CreateAccountResponse
import org.springframework.stereotype.Service

@Service
public class CreateAccountDelegate() {
    public fun invoke(request: CreateAccountRequest): CreateAccountResponse {
        TODO()
    }
}
