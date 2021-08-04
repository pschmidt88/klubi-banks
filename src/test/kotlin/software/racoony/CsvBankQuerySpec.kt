package software.racoony

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class CsvBankQuerySpec {

    @Test
    fun `byBankCode(50010517) returns entry for ING`() {
        val bankInformation = CsvBankQuery().byBankCode(BankCode("50010517"))

        bankInformation.bankName.name shouldBeEqualTo "ING-DiBa"
        bankInformation.bankCode shouldBeEqualTo BankCode("50010517")
    }

    @Test
    fun `byIban(DE78500105175419262594) returns entry for ING`() {
        val bankInformation = CsvBankQuery().byIban(Iban("DE78500105175419262594"))

        bankInformation.bankName.name shouldBeEqualTo "ING-DiBa"
        bankInformation.bankCode shouldBeEqualTo BankCode("50010517")
    }
}