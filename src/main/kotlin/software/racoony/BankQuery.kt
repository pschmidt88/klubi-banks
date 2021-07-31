package software.racoony

interface BankQuery {
    fun byBankCode(bankCode: BankCode): BankInformation
    fun byIban(iban: Iban): BankInformation
}