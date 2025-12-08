package pt.iade.ei.cobuy.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.ui.theme.COBUYTheme

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldFilledPreview() {
    var text by remember { mutableStateOf("Rodrigo") }

    COBUYTheme {
        CustomTextField(
            value = text,
            onValueChange = { text = it },
            label = "Utilizador"
        )
    }
}