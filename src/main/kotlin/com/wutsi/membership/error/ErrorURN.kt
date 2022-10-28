package com.wutsi.membership.error

enum class ErrorURN(val urn: String) {
    ACCOUNT_NOT_FOUND("urn:wutsi:error:membership-access:account-not-found"),
    ACCOUNT_SUSPENDED("urn:wutsi:error:membership-access:account-suspended"),

    ATTRIBUTE_NOT_VALID("urn:wutsi:error:membership-access:attribute-not-valid"),

    CATEGORY_NOT_FOUND("urn:wutsi:error:membership-access:category-not-found"),

    DEVICE_NOT_FOUND("urn:wutsi:error:membership-access:device-not-found"),

    PHONE_NUMBER_ALREADY_ASSIGNED("urn:wutsi:error:membership-access:phone-number-already-assigned"),

    PLACE_NOT_FOUND("urn:wutsi:error:membership-access:place-not-found")
}
