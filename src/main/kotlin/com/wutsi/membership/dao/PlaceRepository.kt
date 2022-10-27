package com.wutsi.membership.dao

import com.wutsi.membership.entity.PlaceEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : CrudRepository<PlaceEntity, Long>
