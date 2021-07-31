package software.racoony

import org.apache.poi.ss.usermodel.WorkbookFactory
import javax.inject.Singleton

private const val BANKCODES_PATH = "/bankcodes/current-banks.xlsx"

@Singleton
class XlsxBankQuery : BankQuery {
    private val bankCodesStore: Map<BankCode, BankInformation>
    private val inputStream = XlsxBankQuery::class.java.getResourceAsStream(BANKCODES_PATH)

    init {
        bankCodesStore = WorkbookFactory.create(inputStream)
            .getSheetAt(0)
            .filter { row -> row.rowNum > 0 }
            .associate { row ->
                val bankCode = BankCode(row.getCell(0).stringCellValue)
                val bic = Bic(row.getCell(7).stringCellValue)
                val bankName = BankName(
                    row.getCell(5).stringCellValue,
                    row.getCell(2).stringCellValue
                )
                bankCode to BankInformation(bankCode, bankName, bic)
            }
    }


    override fun byBankCode(bankCode: BankCode): BankInformation {
        return bankCodesStore[bankCode] ?: throw NoBankInformationFound()
    }

    override fun byIban(iban: Iban): BankInformation {
        return bankCodesStore[iban.bankCode()] ?: throw NoBankInformationFound()
    }
}

class NoBankInformationFound : Exception()