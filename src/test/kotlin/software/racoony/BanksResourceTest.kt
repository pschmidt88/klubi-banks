package software.racoony

import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType

@QuarkusTest
class BanksResourceTest {

    @Test
    fun `searching with bankcode 50010517 returns entry for ing`() {
        Given {
            queryParam("bankCode", "50010517")
        } When {
            get("/api/bank")
        } Then {
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON)
            body("bankCode.value", CoreMatchers.equalTo("50010517"))
            body("bankName.shortName", CoreMatchers.equalTo("ING-DiBa Frankfurt am Main"))
            body("bankName.name", CoreMatchers.equalTo("ING-DiBa"))
            body("bic.value", CoreMatchers.equalTo("INGDDEFFXXX"))
        }
    }

    @Test
    fun `searching with iban from ing returns entry for ing`() {
        Given {
            queryParam("iban", "DE78500105175419262594")
        } When {
            get("/api/bank")
        } Then {
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON)
            body("bankCode.value", CoreMatchers.equalTo("50010517"))
            body("bankName.shortName", CoreMatchers.equalTo("ING-DiBa Frankfurt am Main"))
            body("bankName.name", CoreMatchers.equalTo("ING-DiBa"))
            body("bic.value", CoreMatchers.equalTo("INGDDEFFXXX"))
        }
    }

    @Test
    fun `when no query param given it returns a 400 response`() {
        When {
            get("/api/bank")
        } Then {
            statusCode(400)
        }
    }
}