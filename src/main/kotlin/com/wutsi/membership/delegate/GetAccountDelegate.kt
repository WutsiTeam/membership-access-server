package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.GetAccountResponse
import com.wutsi.membership.service.AccountService
import com.wutsi.platform.core.logging.KVLogger
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
public class GetAccountDelegate(
    private val service: AccountService,
    private val request: HttpServletRequest,
    private val logger: KVLogger
) {
    public fun invoke(id: Long): GetAccountResponse {
        val language = request.getHeader("Accept-Language")
        logger.add("language", language)

        val account = service.findById(id)
        return GetAccountResponse(
            account = service.toAccount(account, language)
        )
    }
}
