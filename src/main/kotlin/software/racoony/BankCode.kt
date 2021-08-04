package software.racoony

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class BankCode(
    val value: String
)
