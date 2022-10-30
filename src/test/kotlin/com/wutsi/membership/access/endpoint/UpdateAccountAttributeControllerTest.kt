package com.wutsi.membership.access.endpoint

import com.fasterxml.jackson.databind.ObjectMapper
import com.wutsi.membership.access.dao.AccountRepository
import com.wutsi.membership.access.dto.UpdateAccountAttributeRequest
import com.wutsi.membership.access.entity.AccountEntity
import com.wutsi.membership.access.error.ErrorURN
import com.wutsi.platform.core.error.ErrorResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = ["/db/clean.sql", "/db/UpdateAccountAttributeController.sql"])
class UpdateAccountAttributeControllerTest {
    @LocalServerPort
    val port: Int = 0

    protected val rest = RestTemplate()

    @Autowired
    private lateinit var dao: AccountRepository

    @Test
    fun `set display-name`() {
        val value = "Omam MBiyick"
        val account = testAttibute("display-name", value)
        assertEquals(value, account.displayName)
    }

    @Test
    fun `set picture-url`() {
        val value = "https://www.i.com/1.png"
        val account = testAttibute("picture-url", value)
        assertEquals(value, account.pictureUrl)
    }

    @Test
    fun `reset picture-url`() {
        val value = null
        val account = testAttibute("picture-url", value)
        assertNull(account.pictureUrl)
    }

    @Test
    fun `empty picture-url`() {
        val value = ""
        val account = testAttibute("picture-url", value)
        assertNull(account.pictureUrl)
    }

    @Test
    fun `invalid-attribute`() {
        val request = UpdateAccountAttributeRequest(
            name = "______fldkflkd",
            value = "value"
        )
        val ex = assertThrows<HttpStatusCodeException> {
            rest.postForEntity(url(100), request, Any::class.java)
        }
        assertEquals(HttpStatus.BAD_REQUEST, ex.statusCode)

        val response = ObjectMapper().readValue(ex.responseBodyAsString, ErrorResponse::class.java)
        assertEquals(ErrorURN.ATTRIBUTE_NOT_VALID.urn, response.error.code)
    }

    @Test
    fun `account suspended`() {
        val request = UpdateAccountAttributeRequest(
            name = "display-name",
            value = "Omam Biyick"
        )
        val ex = assertThrows<HttpStatusCodeException> {
            rest.postForEntity(url(199), request, Any::class.java)
        }
        assertEquals(HttpStatus.NOT_FOUND, ex.statusCode)

        val response = ObjectMapper().readValue(ex.responseBodyAsString, ErrorResponse::class.java)
        assertEquals(ErrorURN.ACCOUNT_SUSPENDED.urn, response.error.code)
    }

    @Test
    fun `reset language`() {
        val value = "en"
        val account = testAttibute("language", value)
        assertEquals(value, account.language)
    }

    @Test
    fun `set country`() {
        val value = "GB"
        val account = testAttibute("country", value)
        assertEquals(value, account.country)
    }

    @Test
    fun `set website`() {
        val value = "https://www.ff.com"
        val account = testAttibute("website", value)
        assertEquals(value, account.website)
    }

    @Test
    fun `set biography`() {
        val value = "This is a biography"
        val account = testAttibute("biography", value)
        assertEquals(value, account.biography)
    }

    @Test
    fun `set category-id`() {
        val value = "1001"
        val account = testAttibute("category-id", value)
        assertEquals(value.toLong(), account.category?.id)
    }

    @Test
    fun `set whatstapp`() {
        val value = "+15147580101"
        val account = testAttibute("whatsapp", value)
        assertEquals(value, account.whatsapp)
    }

    @Test
    fun `set street`() {
        val value = "3030 Linton"
        val account = testAttibute("street", value)
        assertEquals(value, account.street)
    }

    @Test
    fun `set city-id`() {
        val value = "200"
        val account = testAttibute("city-id", value)
        assertEquals(value.toLong(), account.city.id)
    }

    @Test
    fun `set timezone-id`() {
        val value = "Africa/Abidjan"
        val account = testAttibute("timezone-id", value)
        assertEquals(value, account.timezoneId)
    }

    @Test
    fun `set email`() {
        val value = "ray.sponble0101@gmail.com"
        val account = testAttibute("email", value)
        assertEquals(value, account.email)
    }

    @Test
    fun `set facebook-id`() {
        val value = "111"
        val account = testAttibute("facebook-id", value)
        assertEquals(value, account.facebookId)
    }

    @Test
    fun `set instagram-id`() {
        val value = "111"
        val account = testAttibute("instagram-id", value)
        assertEquals(value, account.instagramId)
    }

    @Test
    fun `set twitter-id`() {
        val value = "111"
        val account = testAttibute("twitter-id", value)
        assertEquals(value, account.twitterId)
    }

    @Test
    fun `set youtube-id`() {
        val value = "111"
        val account = testAttibute("youtube-id", value)
        assertEquals(value, account.youtubeId)
    }

    @Test
    fun `set store-id`() {
        val value = "111"
        val account = testAttibute("store-id", value)
        assertEquals(value.toLong(), account.storeId)
    }

    @Test
    fun `set wallet-id`() {
        val value = "111"
        val account = testAttibute("wallet-id", value)
        assertEquals(value.toLong(), account.walletId)
    }

    private fun testAttibute(name: String, value: String?): AccountEntity {
        val request = UpdateAccountAttributeRequest(
            name = name,
            value = value
        )
        val response = rest.postForEntity(url(100), request, Any::class.java)
        assertEquals(200, response.statusCodeValue)

        return dao.findById(100).get()
    }

    private fun url(id: Long) = "http://localhost:$port/v1/accounts/$id/attributes"
}
