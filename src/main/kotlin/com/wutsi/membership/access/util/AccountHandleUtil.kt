package com.wutsi.membership.access.util

object AccountHandleUtil {
    fun generate(displayName: String, maxLength: Int): String {
        val handle = displayName.lowercase().take(maxLength)
            .replace(' ', '-')
        return StringUtil.toAscii(handle)
    }
}
