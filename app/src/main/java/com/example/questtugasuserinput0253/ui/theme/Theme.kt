package com.example.questtugasuserinput0253.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.questtugasuserinput0253.R

@Composable
fun QuestTugasUserInput0253Theme(
    content: @Composable () -> Unit
) {
    val primary = colorResource(R.color.form_primary)
    val background = colorResource(R.color.screen_background)
    val surface = colorResource(R.color.form_panel)

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = primary,
            onPrimary = colorResource(R.color.white),
            background = background,
            surface = surface,
            onSurface = colorResource(R.color.field_text),
            error = colorResource(R.color.error_text)
        ),
        typography = Typography,
        content = content
    )
}