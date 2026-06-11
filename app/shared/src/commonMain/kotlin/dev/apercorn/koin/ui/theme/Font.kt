package dev.apercorn.koin.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import koin.app.shared.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun geistFontFamily() = FontFamily(
	Font(Res.font.geist_variable),
)

@Composable
fun ibmPlexSansFontFamily() = FontFamily(
	Font(Res.font.ibmplexsans_variable),
)

@Composable
fun ibmPlexMonoFontFamily() = FontFamily(
	Font(Res.font.ibmplexmono_regular, weight = FontWeight.Normal),
	Font(Res.font.ibmplexmono_medium, weight = FontWeight.Medium),
	Font(Res.font.ibmplexmono_semibold, weight = FontWeight.SemiBold),
	Font(Res.font.ibmplexmono_bold, weight = FontWeight.Bold)
)

@Composable
fun interFontFamily() = FontFamily(
	Font(Res.font.inter_variable),
)

@Composable
fun satoshiFontFamily() = FontFamily(
	Font(Res.font.satoshi_regular, weight = FontWeight.Normal),
	Font(Res.font.satoshi_medium, weight = FontWeight.Medium),
	Font(Res.font.satoshi_bold, weight = FontWeight.Bold),
	Font(Res.font.satoshi_black, weight = FontWeight.Black)
)
