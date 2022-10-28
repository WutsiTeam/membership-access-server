package com.wutsi.membership.error

enum class ErrorURN(val urn: String) {
    ACCOUNT_NOT_FOUND("urn:wutsi:error:membership:account-not-found"),
    ACCOUNT_SUSPENDED("urn:wutsi:error:membership:account-suspended"),

    ATTRIBUTE_NOT_VALID("urn:wutsi:error:account:attribute-not-valid"),

    CATEGORY_NOT_FOUND("urn:wutsi:error:membership:category-not-found"),

    PHONE_NUMBER_ALREADY_ASSIGNED("urn:wutsi:error:membership:phone-number-already-assigned"),

    PLACE_NOT_FOUND("urn:wutsi:error:membership:place-not-found")
}
