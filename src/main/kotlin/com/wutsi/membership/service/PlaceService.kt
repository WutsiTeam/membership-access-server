package com.wutsi.membership.service

import com.wutsi.membership.dao.PlaceRepository
import com.wutsi.membership.entity.PlaceEntity
import com.wutsi.membership.error.ErrorURN
import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType
import com.wutsi.platform.core.error.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class PlaceService(private val dao: PlaceRepository) {
    fun findById(id: Long): PlaceEntity =
        dao.findById(id).orElseThrow {
            NotFoundException(
                error = Error(
                    code = ErrorURN.PLACE_NOT_FOUND.urn,
                    parameter = Parameter(
                        name = "id",
                        value = id,
                        type = ParameterType.PARAMETER_TYPE_PATH
                    )
                )
            )
        }

    fun getName(place: PlaceEntity, language: String?) =
        when (language?.lowercase()) {
            "fr" -> place.nameFrench
            else -> place.name
        }
}
