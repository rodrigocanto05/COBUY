package pt.iade.ei.cobuy.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.dp, OrangePrimary),
        modifier = modifier.height(52.dp)
    ) {
        Text(
            text = text,
            color = OrangePrimary,
            fontSize = 16.sp
        )
    }
}
