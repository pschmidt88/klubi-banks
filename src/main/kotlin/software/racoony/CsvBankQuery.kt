package software.racoony

import com.opencsv.CSVReader
import java.io.InputStream
import javax.enterprise.context.ApplicationScoped

private const val BANKCODES_PATH = "/bankcodes/current-banks.csv"

@ApplicationScoped
class CsvBankQuery : BankQuery {
    private val bankCodesStore: Map<BankCode, BankInformation>
    private val inputStream: InputStream = CsvBankQuery::class.java.getResourceAsStream(BANKCODES_PATH)

    init {
        val allLines = CSVReader(inputStream.reader())
            .readAll()
        bankCodesStore = allLines
            .drop(1)
            .associate { row ->
                val bankCode = BankCode(row[0])
                val bic = Bic(row[7])
                val bankName = BankName(row[5], row[2])

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