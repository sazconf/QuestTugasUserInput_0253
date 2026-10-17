package com.example.questtugasuserinput0253

import androidx.core.util.PatternsCompat

internal fun isBiodataFormValid(
    firstName: String,
    lastName: String,
    email: String,
    birthDateMillis: Long?,
    experience: String,
    androidSkillSelected: Boolean,
    uiUxSkillSelected: Boolean,
    webSkillSelected: Boolean,
    nowMillis: Long
): Boolean {
    return firstName.trim().isNotEmpty() &&
        lastName.trim().isNotEmpty() &&
        PatternsCompat.EMAIL_ADDRESS.matcher(email.trim()).matches() &&
        birthDateMillis != null &&
        birthDateMillis <= nowMillis &&
        experience.isNotBlank() &&
        (androidSkillSelected || uiUxSkillSelected || webSkillSelected)
}
