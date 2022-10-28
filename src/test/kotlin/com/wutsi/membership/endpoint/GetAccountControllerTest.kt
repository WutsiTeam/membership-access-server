package com.wutsi.membership.endpoint

import com.wutsi.membership.dto.GetAccountResponse
import com.wutsi.membership.entity.PlaceType
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = ["/db/clean.sql", "/db/GetAccountController.sql"])
class GetAccountControllerTest {
    @LocalServerPort
    val port: Int = 0

    private val rest = RestTemplate()

    @Test
    fun get() {
        val response = rest.getForEntity(url(100), GetAccountResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val account = response.body!!.account
        assertEquals(100, account.id)
        assertEquals("Ray Sponsible", account.displayName)
        assertEquals("https://me.com/12343/picture.png", account.pictureUrl)
        assertEquals("ACTIVE", account.status)
        assertEquals("fr", account.language)
        assertEquals("ray.sponsible@gmail.com", account.email)
        assertEquals("GB", account.country)
        assertTrue(account.superUser)
        assertTrue(account.business)
        assertNotNull(account.created)
        assertNotNull(account.updated)
        assertNull(account.suspended)

        assertEquals("+237221234100", account.phone.number)
        assertEquals("CM", account.phone.country)
        assertNotNull(account.phone.created)

        assertEquals(100, account.city.id)
        assertEquals("Yaounde", account.city.name)
        assertEquals("Yaounde, Cameroon", account.city.longName)
        assertEquals("CM", account.city.country)
        assertEquals("Africa/Douala", account.city.timezoneId)
        assertEquals(PlaceType.CITY.name, account.city.type)

        assertNotNull(account.category)
        assertEquals(1000, account.category.id)
        assertEquals("Advertising/Marketing", account.category.title)
    }

    private fun url(id: Long) = "http://localhost:$port/v1/accounts/$id"
}
