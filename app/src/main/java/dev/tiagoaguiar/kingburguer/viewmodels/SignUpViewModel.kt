package dev.tiagoaguiar.kingburguer.viewmodels

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.tiagoaguiar.kingburguer.compose.signup.FieldState
import dev.tiagoaguiar.kingburguer.compose.signup.FormState
import dev.tiagoaguiar.kingburguer.compose.signup.SignUpUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    var formState by mutableStateOf(FormState())

    fun reset() {
        _uiState.update { SignUpUiState() }
    }

    fun updateEmail(newEmail: String) {
        if(newEmail.isBlank()) {
            formState = formState.copy(
                email = FieldState(field = newEmail, error = "Campo e-mail não pode ser vazio")
            )
            return
        }

        if(!isEmailValid(newEmail)) {
            formState = formState.copy(
                name = FieldState(field = newEmail, error = "E-mail inválido. Verifique o campo novamente")
            )
            return
        }

        formState = formState.copy(
            name = FieldState(field = newEmail, error = null)
        )
    }

    fun updateName(newName: String) {
        if(newName.isBlank()) {
            formState = formState.copy(
                name = FieldState(field = newName, error = "Campo nome não pode ser vazio")
            )
            return
        }

        if(newName.length < 3) {
            formState = formState.copy(
                name = FieldState(field = newName, error = "Nome deve ter 3 letras ou mais")
            )
            return
        }

        formState = formState.copy(
            name = FieldState(field = newName, error = null)
        )
    }

    fun updatePassword(newPassword: String) {
        if(newPassword.isBlank()) {
            formState = formState.copy(
                password = FieldState(field = newPassword, error = "Campo senha não pode ser vazio")
            )
            return
        }

        if(newPassword.length < 8) {
            formState = formState.copy(
                password = FieldState(field = newPassword, error = "Senha deve ter 8 caracteres ou mais")
            )
            return
        }

        formState = formState.copy(
            password = FieldState(field = newPassword, error = null)
        )
    }

    fun updatePasswordConfirm(confirmPassword: String) {
        if(confirmPassword.isBlank()) {
            formState = formState.copy(
                confirmPassword = FieldState(field = confirmPassword, error = "Campo confirmar senha não pode ser vazio")
            )
            return
        }

        if(confirmPassword != formState.password.field) {
            formState = formState.copy(
                confirmPassword = FieldState(field = confirmPassword, error = "Confirmar confirmar senha deve ser igual a senha")
            )
            return
        }

        formState = formState.copy(
            confirmPassword = FieldState(field = confirmPassword, error = null)
        )
    }

    fun send() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            delay(3000)

            _uiState.update { it.copy(isLoading = false, goToHome = true) }

            // _uiState.update { it.copy(isLoading = false, error = "Usuario nao encontrado!") }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}