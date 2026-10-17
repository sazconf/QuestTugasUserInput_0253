package com.example.questtugasuserinput0253

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BiodataScreen() {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val panelShape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.panel_corner_radius),
        topEnd = dimensionResource(R.dimen.panel_corner_radius)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.neon_spider_banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.banner_height))
                    .align(Alignment.TopCenter)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = dimensionResource(R.dimen.banner_height) - dimensionResource(R.dimen.panel_overlap))
                    .shadow(dimensionResource(R.dimen.panel_elevation), panelShape),
                shape = panelShape,
                color = colorResource(R.color.form_panel)
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.panel_horizontal_padding),
                        vertical = dimensionResource(R.dimen.panel_vertical_padding)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        color = colorResource(R.color.form_primary),
                        fontSize = dimensionResource(R.dimen.title_size).value.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.form_subtitle),
                        color = colorResource(R.color.subtitle_text),
                        fontSize = dimensionResource(R.dimen.subtitle_size).value.sp,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.title_subtitle_gap))
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.section_gap)))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(
                            dimensionResource(R.dimen.field_gap)
                        )
                    ) {
                        BiodataTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            hint = stringResource(R.string.first_name_hint),
                            modifier = Modifier.weight(1f)
                        )
                        BiodataTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            hint = stringResource(R.string.last_name_hint),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.row_gap)))
                    BiodataTextField(
                        value = email,
                        onValueChange = { email = it },
                        hint = stringResource(R.string.email_hint),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun BiodataTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint) },
        modifier = modifier.height(dimensionResource(R.dimen.field_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner_radius)),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        textStyle = androidx.compose.ui.text.TextStyle(
            color = colorResource(R.color.field_text),
            fontSize = dimensionResource(R.dimen.field_text_size).value.sp
        )
    )
}
