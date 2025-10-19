package dev.tiagoaguiar.kingburguer.compose.component

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    loading: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled && !loading,
            shape = RoundedCornerShape(10.dp),
            onClick = onClick
        ) {
            if(!loading) {
                Text(text.uppercase())
            }
        }
        if(loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp).scale(0.7f)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun KingButtonPreview() {
    KingBurguerTheme {
        Column {
            KingButton("Enviar", enabled = false) {}
            KingButton("Enviar", enabled = true) {}
            KingButton("Enviar", loading = true) {}
            KingButton("Enviar", loading = false) {}
        }
    }
}