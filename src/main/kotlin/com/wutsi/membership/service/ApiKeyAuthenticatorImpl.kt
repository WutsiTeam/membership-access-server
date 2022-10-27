package com.wutsi.membership.service

import com.wutsi.platform.core.security.spring.ApiKeyAuthenticator
import org.springframework.stereotype.Service

@Service
class ApiKeyAuthenticatorImpl : ApiKeyAuthenticator {
    override fun authenticate(apiKey: String): String = ""
}
