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
import dev.tiagoaguiar.kingburguer.validation.EmailValidator
import dev.tiagoaguiar.kingburguer.validation.Mask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    var formState by mutableStateOf(FormState())

    private val emailValidator = EmailValidator()

    fun reset() {
        _uiState.update { SignUpUiState() }
    }

    fun updateEmail(newEmail: String) {
        val textString = emailValidator.validate(newEmail)
        formState = formState.copy(
            email = FieldState(field = newEmail, error = textString)
        )
    }

    fun updateName(newName: String) {
        if (newName.isBlank()) {
            formState = formState.copy(
                name = FieldState(field = newName, error = "Campo nome não pode ser vazio")
            )
            return
        }

        if (newName.length < 3) {
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
        if (newPassword.isBlank()) {
            formState = formState.copy(
                password = FieldState(field = newPassword, error = "Campo senha não pode ser vazio")
            )
            return
        }

        if (newPassword.length < 8) {
            formState = formState.copy(
                password = FieldState(
                    field = newPassword,
                    error = "Senha deve ter 8 caracteres ou mais"
                )
            )
            return
        }

        formState = formState.copy(
            password = FieldState(field = newPassword, error = null)
        )
    }

    fun updatePasswordConfirm(confirmPassword: String) {
        if (confirmPassword.isBlank()) {
            formState = formState.copy(
                confirmPassword = FieldState(
                    field = confirmPassword,
                    error = "Campo confirmar senha não pode ser vazio"
                )
            )
            return
        }

        if (confirmPassword != formState.password.field) {
            formState = formState.copy(
                confirmPassword = FieldState(
                    field = confirmPassword,
                    error = "Confirmar confirmar senha deve ser igual a senha"
                )
            )
            return
        }

        formState = formState.copy(
            confirmPassword = FieldState(field = confirmPassword, error = null)
        )
    }

    fun updateDocument(newDocument: String) {
        val pattern = "###.###.###-##"
        val currentDocument = formState.document.field
        val result = Mask(pattern, currentDocument, newDocument)

        formState = formState.copy(
            document = FieldState(field = result, error = null)
        )

        if (result.isBlank()) {
            formState = formState.copy(
                document = FieldState(field = result, error = "Campo CPF não pode ser vazio")
            )
            return
        }

        if (result.length != pattern.length) {
            formState = formState.copy(
                document = FieldState(field = result, error = "CPF deve ter 11 números")
            )
            return
        }

        formState = formState.copy(
            document = FieldState(field = result, error = null)
        )
    }

    fun updateBirthday(newBirthday: String) {
        val pattern = "##/##/####"
        val currentBirthday = formState.birthday.field
        val result = Mask(pattern, currentBirthday, newBirthday)

        if (result.isBlank()) {
            formState = formState.copy(
                birthday = FieldState(field = result, error = "Campo data de nascimento não pode ser vazio")
            )
            return
        }

        if (result.length != pattern.length) {
            formState = formState.copy(
                birthday = FieldState(field = result, error = "Data de nascimento inválida")
            )
            return
        }

        try {
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).run {
                isLenient = false
                parse(result)
            }?.also {
                val now = Date()
                if (it.after(now)) {
                    formState = formState.copy(
                        birthday = FieldState(
                            field = result,
                            error = "Data de nascimento não pode ser maior que hoje"
                        )
                    )
                    return
                }
            }
            formState = formState.copy(
                birthday = FieldState(field = result, error = null)
            )
        } catch (e: ParseException) {
            formState = formState.copy(
                birthday = FieldState(field = result, error = "Data de nascimento inválida")
            )
        }
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