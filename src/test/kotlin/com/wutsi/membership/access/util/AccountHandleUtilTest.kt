package com.wutsi.membership.access.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AccountHandleUtilTest {
    @Test
    fun generate() {
        assertEquals("ray-sponsible", AccountHandleUtil.generate("Ray Sponsible", 30))
        assertEquals("ray-sponsi", AccountHandleUtil.generate("Ray Sponsible", 10))
    }
}
