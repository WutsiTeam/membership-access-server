package com.wutsi.membership.service

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.wutsi.membership.dao.PhoneRepository
import com.wutsi.membership.entity.PhoneEntity
import org.springframework.stereotype.Service

@Service
class PhoneService(private val dao: PhoneRepository) {
    fun findOrCreate(phoneNumber: String): PhoneEntity {
        val util = PhoneNumberUtil.getInstance()
        val phone = util.parse(phoneNumber, "")
        val number = util.format(phone, PhoneNumberUtil.PhoneNumberFormat.E164)

        return dao.findByNumber(number).orElseGet {
            dao.save(
                PhoneEntity(
                    number = number,
                    country = util.getRegionCodeForCountryCode(phone.countryCode)
                )
            )
        }
    }
}
