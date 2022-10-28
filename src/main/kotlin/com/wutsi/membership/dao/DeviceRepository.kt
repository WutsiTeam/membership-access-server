package com.wutsi.membership.dao

import com.wutsi.membership.entity.DeviceEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : CrudRepository<DeviceEntity, Long>
