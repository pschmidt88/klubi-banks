package software.racoony

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class Bic(val value: String)