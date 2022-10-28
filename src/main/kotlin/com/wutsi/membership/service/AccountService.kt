package com.wutsi.membership.service

import com.wutsi.membership.dao.AccountRepository
import com.wutsi.membership.dto.CreateAccountRequest
import com.wutsi.membership.dto.UpdateAccountAttributeRequest
import com.wutsi.membership.entity.AccountEntity
import com.wutsi.membership.entity.AccountStatus
import com.wutsi.membership.entity.PhoneEntity
import com.wutsi.membership.error.ErrorURN
import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType
import com.wutsi.platform.core.error.exception.BadRequestException
import com.wutsi.platform.core.error.exception.ConflictException
import com.wutsi.platform.core.error.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val dao: AccountRepository,
    private val placeService: PlaceService,
    private val phoneService: PhoneService,
    private val categoryService: CategoryService
) {
    companion object {
        const val DEFAULT_LANGUAGE = "en"
        const val DEFAULT_COUNTRY = "US"
    }

    fun create(request: CreateAccountRequest): AccountEntity {
        // Phone number
        val phone = phoneService.findOrCreate(request.phoneNumber)
        ensureNotAssigned(phone)

        // Account
        val city = placeService.findById(request.cityId)
        return dao.save(
            AccountEntity(
                phone = phone,
                city = city,
                country = request.country,
                displayName = request.displayName,
                language = request.language,
                pictureUrl = request.pictureUrl,
                status = AccountStatus.ACTIVE,
                timezoneId = city.timezoneId
            )
        )
    }

    fun update(id: Long, request: UpdateAccountAttributeRequest): AccountEntity {
        val account = findById(id)

        when (request.name.lowercase()) {
            "display-name" -> account.displayName = toString(request.value)!!
            "picture-url" -> account.pictureUrl = request.value
            "language" -> account.language = request.value ?: DEFAULT_LANGUAGE
            "country" -> account.country = request.value ?: DEFAULT_COUNTRY
            "biography" -> account.biography = toString(request.value)
            "website" -> account.website = toString(request.value)
            "category-id" -> account.category = toLong(request.value)?.let { categoryService.findById(it) }
            "whatsapp" -> account.whatsapp = toString(request.value)
            "street" -> account.street = toString(request.value)
            "city-id" -> account.city = toLong(request.value)!!.let { placeService.findById(it) }
            "timezone-id" -> account.timezoneId = toString(request.value)
            "email" -> account.email = toString(request.value)
            "facebook-id" -> account.facebookId = toString(request.value)
            "instagram-id" -> account.instagramId = toString(request.value)
            "twitter-id" -> account.twitterId = toString(request.value)
            "youtube-id" -> account.youtubeId = toString(request.value)
            else -> throw BadRequestException(
                error = Error(
                    code = ErrorURN.ATTRIBUTE_NOT_VALID.urn,
                    parameter = Parameter(
                        name = "name",
                        value = request.name,
                        type = ParameterType.PARAMETER_TYPE_PAYLOAD
                    )
                )
            )
        }
        dao.save(account)
        return account
    }

    fun findById(id: Long, acceptSuspended: Boolean = false): AccountEntity {
        val account = dao.findById(id)
            .orElseThrow {
                NotFoundException(
                    error = Error(
                        code = ErrorURN.ACCOUNT_NOT_FOUND.urn,
                        parameter = Parameter(
                            name = "id",
                            value = id,
                            type = ParameterType.PARAMETER_TYPE_PATH
                        )
                    )
                )
            }

        if (account.status == AccountStatus.SUSPENDED && !acceptSuspended) {
            throw NotFoundException(
                error = Error(
                    code = ErrorURN.ACCOUNT_SUSPENDED.urn,
                    parameter = Parameter(
                        name = "id",
                        value = id,
                        type = ParameterType.PARAMETER_TYPE_PATH
                    )
                )
            )
        }
        return account
    }

    private fun ensureNotAssigned(phone: PhoneEntity) {
        val account = dao.findByPhoneAndStatus(phone, AccountStatus.ACTIVE)
        if (account.isPresent) {
            throw ConflictException(
                error = Error(
                    code = ErrorURN.PHONE_NUMBER_ALREADY_ASSIGNED.urn,
                    parameter = Parameter(
                        name = "phoneNumber",
                        type = ParameterType.PARAMETER_TYPE_PAYLOAD,
                        value = phone.number
                    )
                )
            )
        }
    }

    private fun toBoolean(value: String?): Boolean =
        value?.lowercase() == "true"

    private fun toString(value: String?): String? =
        if (value.isNullOrEmpty()) {
            null
        } else {
            value
        }

    private fun toLong(value: String?): Long? =
        toString(value)?.toLong()
}
