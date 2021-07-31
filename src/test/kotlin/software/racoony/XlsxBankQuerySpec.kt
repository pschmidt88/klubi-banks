package software.racoony

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class XlsxBankQuerySpec {

    @Test
    fun `byBankCode(50010517) returns entry for ING`() {
        val bankInformation = XlsxBankQuery().byBankCode(BankCode("50010517"))

        bankInformation.bankName.name shouldBeEqualTo "ING-DiBa"
        bankInformation.bankCode shouldBeEqualTo BankCode("50010517")
    }

    @Test
    fun `byIban(DE78500105175419262594) returns entry for ING`() {
        val bankInformation = XlsxBankQuery().byIban(Iban("DE78500105175419262594"))

        bankInformation.bankName.name shouldBeEqualTo "ING-DiBa"
        bankInformation.bankCode shouldBeEqualTo BankCode("50010517")
    }
}