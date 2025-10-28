package dev.tiagoaguiar.kingburguer.compose.signup


data class FieldState(
    val field: String = "",
    val error: String? = null,
)
data class FormState(
    val name: FieldState = FieldState(),
    val password: FieldState = FieldState(),
    val confirmPassword: FieldState = FieldState()
)