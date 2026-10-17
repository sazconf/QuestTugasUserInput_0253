package com.example.questtugasuserinput0253

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.zIndex
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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
    var birthDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
    var experience by rememberSaveable { mutableStateOf("") }
    var experienceMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var androidSkillSelected by rememberSaveable { mutableStateOf(false) }
    var uiUxSkillSelected by rememberSaveable { mutableStateOf(false) }
    var webSkillSelected by rememberSaveable { mutableStateOf(false) }
    var showSubmittedData by rememberSaveable { mutableStateOf(false) }
    val formIsValid = isBiodataFormValid(
        firstName = firstName,
        lastName = lastName,
        email = email,
        birthDateMillis = birthDateMillis,
        experience = experience,
        androidSkillSelected = androidSkillSelected,
        uiUxSkillSelected = uiUxSkillSelected,
        webSkillSelected = webSkillSelected,
        nowMillis = System.currentTimeMillis()
    )
    val emailIsInvalid = email.isNotBlank() &&
        !androidx.core.util.PatternsCompat.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val skillsAreMissing = firstName.isNotBlank() &&
        lastName.isNotBlank() &&
        !emailIsInvalid &&
        birthDateMillis != null &&
        experience.isNotBlank() &&
        !androidSkillSelected && !uiUxSkillSelected && !webSkillSelected
    val experienceOptions = androidx.compose.ui.res.stringArrayResource(R.array.experience_options)
    val selectedBirthDate = birthDateMillis?.let {
        SimpleDateFormat(stringResource(R.string.date_format), Locale.getDefault()).format(it)
    }.orEmpty()
    val selectedSkills = listOf(
        if (androidSkillSelected) stringResource(R.string.skill_android) else null,
        if (uiUxSkillSelected) stringResource(R.string.skill_ui_ux) else null,
        if (webSkillSelected) stringResource(R.string.skill_web) else null
    ).filterNotNull().joinToString(stringResource(R.string.skill_separator))
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
                contentDescription = stringResource(R.string.banner_description),
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
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .imePadding()
                        .padding(
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
                        isError = emailIsInvalid,
                        errorText = if (emailIsInvalid) {
                            stringResource(R.string.validation_email)
                        } else {
                            null
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.row_gap)))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(
                            dimensionResource(R.dimen.field_gap)
                        )
                    ) {
                        BirthDateField(
                            selectedDateMillis = birthDateMillis,
                            onDateSelected = { birthDateMillis = it },
                            modifier = Modifier.weight(1f)
                        )
                        ExperienceField(
                            value = experience,
                            options = experienceOptions,
                            expanded = experienceMenuExpanded,
                            onExpandedChange = { experienceMenuExpanded = it },
                            onOptionSelected = {
                                experience = it
                                experienceMenuExpanded = false
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (skillsAreMissing) {
                        Text(
                            text = stringResource(R.string.validation_skill),
                            color = colorResource(R.color.error_text),
                            fontSize = dimensionResource(R.dimen.skill_text_size).value.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(R.dimen.title_subtitle_gap))
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.section_gap)))
                    Text(
                        text = stringResource(R.string.skills_title),
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.field_text),
                        fontSize = dimensionResource(R.dimen.field_text_size).value.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.title_subtitle_gap)),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SkillCheckbox(
                            label = stringResource(R.string.skill_android),
                            checked = androidSkillSelected,
                            onCheckedChange = { androidSkillSelected = it },
                            testTag = "skill_android"
                        )
                        SkillCheckbox(
                            label = stringResource(R.string.skill_ui_ux),
                            checked = uiUxSkillSelected,
                            onCheckedChange = { uiUxSkillSelected = it },
                            testTag = "skill_ui_ux"
                        )
                        SkillCheckbox(
                            label = stringResource(R.string.skill_web),
                            checked = webSkillSelected,
                            onCheckedChange = { webSkillSelected = it },
                            testTag = "skill_web"
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.section_gap)))
                    Button(
                        onClick = { showSubmittedData = true },
                        enabled = formIsValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(R.dimen.button_height)),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.form_primary),
                            contentColor = colorResource(R.color.white),
                            disabledContainerColor = colorResource(R.color.disabled_button),
                            disabledContentColor = colorResource(R.color.white)
                        )
                    ) {
                        Text(text = stringResource(R.string.proceed))
                    }
                    if (showSubmittedData) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(R.dimen.section_gap)),
                            shape = RoundedCornerShape(
                                dimensionResource(R.dimen.field_corner_radius)
                            ),
                            color = colorResource(R.color.screen_background)
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    dimensionResource(R.dimen.panel_vertical_padding)
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.submission_success_title),
                                    color = colorResource(R.color.form_primary),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = dimensionResource(R.dimen.field_text_size).value.sp
                                )
                                Text(
                                    text = stringResource(
                                        R.string.submission_success_message,
                                        firstName.trim(),
                                        lastName.trim(),
                                        email.trim(),
                                        selectedBirthDate,
                                        experience,
                                        selectedSkills
                                    ),
                                    color = colorResource(R.color.field_text),
                                    fontSize = dimensionResource(R.dimen.field_text_size).value.sp,
                                    modifier = Modifier.padding(
                                        top = dimensionResource(R.dimen.title_subtitle_gap)
                                    )
                                )
                            }
                        }
                    }
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    errorText: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint) },
        modifier = modifier.height(dimensionResource(R.dimen.field_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner_radius)),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.form_primary),
            unfocusedBorderColor = colorResource(R.color.field_border),
            cursorColor = colorResource(R.color.form_primary),
            focusedTextColor = colorResource(R.color.field_text),
            unfocusedTextColor = colorResource(R.color.field_text),
            errorTextColor = colorResource(R.color.field_text),
            focusedPlaceholderColor = colorResource(R.color.subtitle_text),
            unfocusedPlaceholderColor = colorResource(R.color.subtitle_text)
        ),
        isError = isError,
        supportingText = errorText?.let { message -> { Text(message) } },
        textStyle = androidx.compose.ui.text.TextStyle(
            color = colorResource(R.color.field_text),
            fontSize = dimensionResource(R.dimen.field_text_size).value.sp
        )
    )
}

@Composable
private fun BirthDateField(
    selectedDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val displayValue = selectedDateMillis?.let {
        SimpleDateFormat(stringResource(R.string.date_format), Locale.getDefault()).format(it)
    }.orEmpty()

    Box(
        modifier = modifier.height(dimensionResource(R.dimen.field_height))
    ) {
        OutlinedTextField(
            value = displayValue,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(text = stringResource(R.string.birth_date_hint)) },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.select_birth_date)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner_radius)),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.form_primary),
                unfocusedBorderColor = colorResource(R.color.field_border),
                cursorColor = colorResource(R.color.form_primary)
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = colorResource(R.color.field_text),
                fontSize = dimensionResource(R.dimen.field_text_size).value.sp
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag("birth_date_field")
                .clickable {
                    showBirthDatePicker(context, calendar, onDateSelected)
                }
        )
    }
}

private fun showBirthDatePicker(
    context: Context,
    initialDate: Calendar,
    onDateSelected: (Long) -> Unit
) {
    DatePickerDialog(
        context,
        { _, year, month, day ->
            val selected = Calendar.getInstance().apply {
                set(year, month, day, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            onDateSelected(selected.timeInMillis)
        },
        initialDate.get(Calendar.YEAR),
        initialDate.get(Calendar.MONTH),
        initialDate.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.maxDate = System.currentTimeMillis()
        setTitle(context.getString(R.string.select_birth_date))
    }.show()
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExperienceField(
    value: String,
    options: Array<String>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(text = stringResource(R.string.experience_hint)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.field_height))
                .menuAnchor(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner_radius)),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.form_primary),
                unfocusedBorderColor = colorResource(R.color.field_border),
                cursorColor = colorResource(R.color.form_primary)
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = colorResource(R.color.field_text),
                fontSize = dimensionResource(R.dimen.field_text_size).value.sp
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { onOptionSelected(option) }
                )
            }
        }
    }
}

@Composable
private fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    testTag: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(dimensionResource(R.dimen.checkbox_size))
                .testTag(testTag)
        )
        Text(
            text = label,
            color = colorResource(R.color.field_text),
            fontSize = dimensionResource(R.dimen.skill_text_size).value.sp,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.title_subtitle_gap))
        )
    }
}
