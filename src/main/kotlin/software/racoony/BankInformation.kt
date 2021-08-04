package software.racoony

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class BankInformation(
    val bankCode: BankCode,
    val bankName: BankName,
    val bic: Bic
)
