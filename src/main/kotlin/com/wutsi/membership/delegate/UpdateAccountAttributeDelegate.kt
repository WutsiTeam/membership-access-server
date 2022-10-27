package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.UpdateAccountAttributeRequest
import org.springframework.stereotype.Service
import kotlin.Long
import kotlin.String

@Service
public class UpdateAccountAttributeDelegate() {
    public fun invoke(
        id: Long,
        name: String,
        request: UpdateAccountAttributeRequest
    ) {
        TODO()
    }
}
