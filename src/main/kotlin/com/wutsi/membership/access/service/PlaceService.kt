package com.wutsi.membership.access.service

import com.wutsi.enums.PlaceType
import com.wutsi.membership.access.dao.PlaceRepository
import com.wutsi.membership.access.dto.Place
import com.wutsi.membership.access.dto.PlaceSummary
import com.wutsi.membership.access.dto.SavePlaceRequest
import com.wutsi.membership.access.dto.SearchPlaceRequest
import com.wutsi.membership.access.entity.PlaceEntity
import com.wutsi.membership.access.error.ErrorURN
import com.wutsi.membership.access.util.StringUtil
import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType
import com.wutsi.platform.core.error.exception.NotFoundException
import org.springframework.stereotype.Service
import java.text.Normalizer
import java.util.Locale
import javax.persistence.EntityManager
import javax.persistence.Query

@Service
class PlaceService(
    private val dao: PlaceRepository,
    private val em: EntityManager,
) {
    companion object {
        private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    }

    fun findById(id: Long): PlaceEntity =
        dao.findById(id).orElseThrow {
            NotFoundException(
                error = Error(
                    code = ErrorURN.PLACE_NOT_FOUND.urn,
                    parameter = Parameter(
                        name = "id",
                        value = id,
                        type = ParameterType.PARAMETER_TYPE_PATH,
                    ),
                ),
            )
        }

    fun save(request: SavePlaceRequest): PlaceEntity {
        val place = dao.findById(request.id)
            .orElse(PlaceEntity())
        place.id = request.id
        place.name = request.name
        place.nameAscii = StringUtil.toAscii(request.name)
        place.longitude = request.longitude
        place.latitude = request.latitude
        place.timezoneId = request.timezoneId
        place.type = PlaceType.valueOf(request.type.uppercase())
        place.country = request.country.uppercase()
        return dao.save(place)
    }

    fun getLongName(place: PlaceEntity, language: String?): String {
        val locale = Locale(language ?: "en", place.country)
        val displayCountry = locale.getDisplayCountry(locale)
        return "${place.name}, $displayCountry"
    }

    fun search(request: SearchPlaceRequest): List<PlaceEntity> {
        val query = em.createQuery(sql(request))
        parameters(request, query)
        return query
            .setFirstResult(request.offset)
            .setMaxResults(request.limit)
            .resultList as List<PlaceEntity>
    }

    fun toPlace(place: PlaceEntity, language: String?) = Place(
        id = place.id,
        name = place.name,
        longName = getLongName(place, language),
        latitude = place.latitude,
        longitude = place.longitude,
        country = place.country,
        type = place.type.name,
        timezoneId = place.timezoneId,
    )

    fun toPlaceSummary(place: PlaceEntity, language: String?) = PlaceSummary(
        id = place.id,
        name = place.name,
        longName = getLongName(place, language),
        country = place.country,
        type = place.type.name,
    )

    private fun sql(request: SearchPlaceRequest): String {
        val select = select()
        val where = where(request)
        return if (where.isNullOrEmpty()) {
            select
        } else {
            "$select WHERE $where ORDER BY a.nameAscii"
        }
    }

    private fun select(): String =
        "SELECT a FROM PlaceEntity a"

    private fun where(request: SearchPlaceRequest): String {
        val criteria = mutableListOf<String>()

        if (!request.country.isNullOrEmpty()) {
            criteria.add("a.country=:country")
        }
        if (request.type != null) {
            criteria.add("a.type = :type")
        }
        if (request.keyword != null) {
            criteria.add("UCASE(a.nameAscii) LIKE :keyword")
        }
        return criteria.joinToString(separator = " AND ")
    }

    private fun parameters(request: SearchPlaceRequest, query: Query) {
        if (!request.country.isNullOrEmpty()) {
            query.setParameter("country", request.country.uppercase())
        }
        if (request.type != null) {
            query.setParameter("type", PlaceType.valueOf(request.type.uppercase()))
        }
        if (request.keyword != null) {
            query.setParameter("keyword", unaccent(request.keyword).uppercase() + "%")
        }
    }

    fun unaccent(str: String?): String {
        if (str == null) {
            return ""
        }

        val temp = Normalizer.normalize(str, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }
}
