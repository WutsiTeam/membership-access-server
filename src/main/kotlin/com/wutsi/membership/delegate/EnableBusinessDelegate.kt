package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.EnableBusinessRequest
import com.wutsi.membership.service.AccountService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
public class EnableBusinessDelegate(private val service: AccountService) {
    @Transactional
    public fun invoke(id: Long, request: EnableBusinessRequest) {
        service.enableBusiness(id, request)
    }
}
