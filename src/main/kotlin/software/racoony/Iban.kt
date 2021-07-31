package software.racoony

data class Iban(
    val value: String
) {
    fun bankCode(): BankCode {
        if (this.value.startsWith("DE", true)) {
            return BankCode(this.value.substring(4, 12))
        }

        throw Error("Only supporting german IBANs for now.")
    }
}
