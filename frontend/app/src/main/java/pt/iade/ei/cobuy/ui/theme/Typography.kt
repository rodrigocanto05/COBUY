package pt.iade.ei.cobuy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular,  FontWeight.Normal),
    Font(R.font.poppins_medium,   FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)

val COBUYTypography = Typography(
    headlineLarge = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
    headlineSmall = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Medium,  fontSize = 20.sp),
    bodyLarge     = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Normal,  fontSize = 16.sp),
    bodyMedium    = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Normal,  fontSize = 14.sp),
    labelLarge    = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Medium,  fontSize = 14.sp)
)
