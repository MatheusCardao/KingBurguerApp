package dev.tiagoaguiar.kingburguer.validation

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.tiagoaguiar.kingburguer.R

interface TextString {
    @get: Composable
    val value: String
}

class ResourceString(@StringRes private val input: Int) : TextString {
    override val value: String
        @Composable
        get() = stringResource(input)

}

class RawString(private val input: String) : TextString {
    override val value: String
        @Composable
        get() = input
}

class EmailValidator {

    fun validate(email: String): TextString? {
        if (email.isBlank()) {
            return ResourceString(R.string.error_email_blank)
        }

        if (!isEmailValid(email)) {
            return RawString("E-mail inválido. Verifique o campo novamente")
        }

        return null
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
