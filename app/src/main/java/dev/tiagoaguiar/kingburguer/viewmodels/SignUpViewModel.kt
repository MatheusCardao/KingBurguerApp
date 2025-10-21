package dev.tiagoaguiar.kingburguer.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.tiagoaguiar.kingburguer.compose.login.LoginUiState
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

        //sucesso
        formState = formState.copy(
            name = FieldState(field = newName, error = null)
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
}