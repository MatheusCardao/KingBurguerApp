package dev.tiagoaguiar.kingburguer.compose.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tiagoaguiar.kingburguer.R
import dev.tiagoaguiar.kingburguer.compose.component.KingAlert
import dev.tiagoaguiar.kingburguer.compose.component.KingButton
import dev.tiagoaguiar.kingburguer.compose.component.KingTextField
import dev.tiagoaguiar.kingburguer.compose.component.KingTextTitle
import dev.tiagoaguiar.kingburguer.ui.theme.KingBurguerTheme
import dev.tiagoaguiar.kingburguer.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    onNavigationClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.login)) },
                    navigationIcon = {
                        IconButton(onClick = { onNavigationClick }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back) )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { contentPadding ->
            SignUpContentScreen(
                signUpViewModel = signUpViewModel,
                modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
            )
        }
    }
}

@Composable
private fun SignUpContentScreen(
    signUpViewModel: SignUpViewModel,
    modifier: Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        var passwordHidden by remember { mutableStateOf(true) }

        val uiState by signUpViewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                uiState.error?.let {
                    KingAlert(
                        onDismissRequest = {},
                        onConfirmation = {
                            signUpViewModel.reset()
                        },
                        dialogTitle = stringResource(id = R.string.app_name),
                        dialogText = it,
                        icon = Icons.Filled.Info
                    )
                }

                KingTextTitle(text = stringResource(id = R.string.signup))

                KingTextField(
                    value = signUpViewModel.email,
                    label = R.string.email,
                    placeholder = R.string.hint_email,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ) {

                }
                KingTextField(
                    value = signUpViewModel.name,
                    label = R.string.name,
                    placeholder = R.string.hint_name,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ) {

                }
                KingTextField(
                    value = signUpViewModel.password,
                    label = R.string.password,
                    placeholder = R.string.hint_password,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    ofuscate = passwordHidden,
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val image =
                                if (passwordHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                            val description =
                                if (passwordHidden) stringResource(id = R.string.show_password) else stringResource(
                                    id = R.string.hide_password
                                )
                            Icon(imageVector = image, contentDescription = description)
                        }
                    }
                ) {

                }
                KingTextField(
                    value = signUpViewModel.confirmPassword,
                    label = R.string.confirm_password,
                    placeholder = R.string.hint_confirm_password,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    ofuscate = passwordHidden,
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val image =
                                if (passwordHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                            val description =
                                if (passwordHidden) stringResource(id = R.string.show_password) else stringResource(
                                    id = R.string.hide_password
                                )
                            Icon(imageVector = image, contentDescription = description)
                        }
                    }
                ) {

                }
                KingTextField(
                    value = signUpViewModel.document,
                    label = R.string.document,
                    placeholder = R.string.hint_document,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ) {

                }
                KingTextField(
                    value = signUpViewModel.birthdate,
                    label = R.string.birthdate,
                    placeholder = R.string.hint_birthdate,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ) {

                }
                KingButton(
                    text = stringResource(id = R.string.sign_up),
                    enabled = true
                ) {
                    signUpViewModel.send()
                }
            }
            Image(
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.BottomCenter,
                painter = painterResource(id = R.drawable.cover3),
                contentDescription = stringResource(id = R.string.hamburguer)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LightSignUpScreenPreview() {
    KingBurguerTheme(dynamicColor = false, darkTheme = false) {
        SignUpScreen {}
    }
}

@Composable
@Preview(showBackground = true)
fun DarkSignUpScreenPreview() {
    KingBurguerTheme(dynamicColor = false, darkTheme = true) {
        SignUpScreen {}
    }
}