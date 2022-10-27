package com.wutsi.membership.dao

import com.wutsi.membership.entity.CategoryEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<CategoryEntity, Long>
