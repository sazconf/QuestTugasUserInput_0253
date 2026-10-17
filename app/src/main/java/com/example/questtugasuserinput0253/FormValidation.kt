package com.example.questtugasuserinput0253

import android.util.Patterns

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
        Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() &&
        birthDateMillis != null &&
        birthDateMillis <= nowMillis &&
        experience.isNotBlank() &&
        (androidSkillSelected || uiUxSkillSelected || webSkillSelected)
}
