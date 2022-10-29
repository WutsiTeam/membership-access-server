package com.wutsi.membership.access.endpoint

import com.wutsi.membership.access.dto.SearchAccountRequest
import com.wutsi.membership.access.dto.SearchAccountResponse
import com.wutsi.membership.access.enums.AccountStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = ["/db/clean.sql", "/db/SearchAccountController.sql"])
class SearchAccountControllerTest {
    @LocalServerPort
    val port: Int = 0

    private val rest = RestTemplate()

    private lateinit var url: String

    @BeforeEach
    fun setUp() {
        url = "http://localhost:$port/v1/accounts/search"
    }

    @Test
    fun `search by phone`() {
        val request = SearchAccountRequest(
            phoneNumber = "+237221234100"
        )
        val response = rest.postForEntity(url, request, SearchAccountResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val accounts = response.body!!.accounts
        assertEquals(1, accounts.size)

        val account = accounts[0]
        assertEquals(100, account.id)
        assertEquals("Ray Sponsible", account.displayName)
        assertEquals("https://me.com/12343/picture.png", account.pictureUrl)
        assertEquals("ACTIVE", account.status)
        assertEquals("fr", account.language)
        assertNotNull(account.created)
        assertNotNull(account.updated)
        assertTrue(account.superUser)
    }

    @Test
    fun `search by phone to normalize`() {
        val request = SearchAccountRequest(
            phoneNumber = " 237221234100"
        )
        val response = rest.postForEntity(url, request, SearchAccountResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val accounts = response.body!!.accounts
        assertEquals(1, accounts.size)

        val account = accounts[0]
        assertEquals(100, account.id)
        assertEquals("Ray Sponsible", account.displayName)
        assertEquals("https://me.com/12343/picture.png", account.pictureUrl)
        assertEquals("ACTIVE", account.status)
        assertEquals("fr", account.language)
        assertNotNull(account.created)
        assertNotNull(account.updated)
        assertTrue(account.superUser)
    }

    @Test
    fun `search by phone and status`() {
        val request = SearchAccountRequest(
            phoneNumber = "+237221234113",
            status = AccountStatus.SUSPENDED.name
        )
        val response = rest.postForEntity(url, request, SearchAccountResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val accounts = response.body!!.accounts
        assertEquals(1, accounts.size)

        val account = accounts[0]
        assertEquals(300, account.id)
    }

    @Test
    fun `search by IDs`() {
        val request = SearchAccountRequest(
            accountIds = listOf(100L, 101L)
        )
        val response = rest.postForEntity(url, request, SearchAccountResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val accounts = response.body!!.accounts.sortedBy { it.id }
        assertEquals(2, accounts.size)
        assertEquals(100, accounts[0].id)
        assertEquals(101, accounts[1].id)
    }
}
