package software.racoony

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api/bank")
class BanksResource(
    private val bankQuery: BankQuery
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun fetchBank(
        @QueryParam("bankCode") bankCode: BankCode?,
        @QueryParam("iban") iban: Iban?
    ): Response {

        if (bankCode != null) {
            return Response.ok(bankQuery.byBankCode(bankCode)).build()
        }

        if (iban != null) {
            return Response.ok(bankQuery.byIban(iban)).build()
        }

        return Response.status(400).build()
    }
}
