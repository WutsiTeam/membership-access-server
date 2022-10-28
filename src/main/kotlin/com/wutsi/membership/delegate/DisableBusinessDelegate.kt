package com.wutsi.membership.`delegate`

import com.wutsi.membership.service.AccountService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
public class DisableBusinessDelegate(private val service: AccountService) {
    @Transactional
    public fun invoke(id: Long) {
        service.disableBusiness(id)
    }
}
