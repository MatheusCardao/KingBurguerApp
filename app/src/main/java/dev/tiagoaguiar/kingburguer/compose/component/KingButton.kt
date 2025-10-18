package dev.tiagoaguiar.kingburguer.compose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tiagoaguiar.kingburguer.R
import dev.tiagoaguiar.kingburguer.ui.theme.KingBurguerTheme


@Composable
fun KingButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Text(text.uppercase())
    }
}


@Composable
@Preview(showBackground = true)
fun KingButtonPreview() {
    KingBurguerTheme {
        KingButton("Enviar") {}
    }
}