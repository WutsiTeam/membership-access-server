package com.wutsi.membership.dao

import com.wutsi.membership.entity.AccountEntity
import com.wutsi.membership.entity.PhoneEntity
import com.wutsi.membership.enums.AccountStatus
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AccountRepository : CrudRepository<AccountEntity, Long> {
    fun findByPhoneAndStatus(
        phone: PhoneEntity,
        status: AccountStatus
    ): Optional<AccountEntity>
}
