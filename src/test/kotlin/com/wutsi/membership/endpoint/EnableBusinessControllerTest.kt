package com.wutsi.membership.endpoint

import com.wutsi.membership.dao.AccountRepository
import com.wutsi.membership.dto.EnableBusinessRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = ["/db/clean.sql", "/db/EnableBusinessController.sql"])
class EnableBusinessControllerTest {
    @LocalServerPort
    val port: Int = 0

    private val rest = RestTemplate()

    @Autowired
    private lateinit var dao: AccountRepository

    @Test
    fun enable() {
        // WHEN
        val request = EnableBusinessRequest(
            displayName = "Yo Inc",
            categoryId = 1001L,
            cityId = 200,
            biography = "This is the biography",
            whatsapp = "+5147580000",
            street = "3030 linton",
            country = "GB"
        )
        rest.postForEntity(url(100), request, Any::class.java)

        // THEN
        val account = dao.findById(100).get()
        assertTrue(account.business)
        assertEquals(request.displayName, account.displayName)
        assertEquals(request.categoryId, account.category?.id)
        assertEquals(request.cityId, account.city.id)
        assertEquals(request.biography, account.biography)
        assertEquals(request.whatsapp, account.whatsapp)
        assertEquals(request.country, account.country)
        assertEquals(request.street, account.street)
    }

    private fun url(id: Long) = "http://localhost:$port/v1/accounts/$id/business"
}
