package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.UpdateAccountStatusRequest
import com.wutsi.membership.service.AccountService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
public class UpdateAccountStatusDelegate(private val service: AccountService) {
    @Transactional
    public fun invoke(id: Long, request: UpdateAccountStatusRequest) {
        service.status(id, request)
    }
}
