package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.UpdateAccountRequest
import com.wutsi.membership.dto.UpdateAccountResponse
import org.springframework.stereotype.Service
import kotlin.Long

@Service
public class UpdateAccountDelegate() {
    public fun invoke(id: Long, request: UpdateAccountRequest): UpdateAccountResponse {
        TODO()
    }
}
