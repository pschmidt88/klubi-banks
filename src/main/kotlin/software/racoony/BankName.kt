package software.racoony

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class BankName(
    val shortName: String,
    val name: String
)
