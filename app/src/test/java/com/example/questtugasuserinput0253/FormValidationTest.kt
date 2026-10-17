package com.example.questtugasuserinput0253

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FormValidationTest {
    private val now = 1_000_000L

    @Test
    fun acceptsCompleteFormWithOneSkill() {
        assertTrue(
            isBiodataFormValid(
                firstName = "Ada",
                lastName = "Lovelace",
                email = "ada@example.com",
                birthDateMillis = now - 1L,
                experience = "Beginner",
                androidSkillSelected = true,
                uiUxSkillSelected = false,
                webSkillSelected = false,
                nowMillis = now
            )
        )
    }

    @Test
    fun rejectsFutureBirthDateAndMissingSkill() {
        assertFalse(
            isBiodataFormValid(
                firstName = "Ada",
                lastName = "Lovelace",
                email = "ada@example.com",
                birthDateMillis = now + 1L,
                experience = "Beginner",
                androidSkillSelected = false,
                uiUxSkillSelected = false,
                webSkillSelected = false,
                nowMillis = now
            )
        )
    }

    @Test
    fun rejectsInvalidEmail() {
        assertFalse(
            isBiodataFormValid(
                firstName = "Ada",
                lastName = "Lovelace",
                email = "not-an-email",
                birthDateMillis = now,
                experience = "Advanced",
                androidSkillSelected = false,
                uiUxSkillSelected = true,
                webSkillSelected = false,
                nowMillis = now
            )
        )
    }

    @Test
    fun trimsNamesAndEmailBeforeValidation() {
        assertTrue(
            isBiodataFormValid(
                firstName = "  Ada ",
                lastName = " Lovelace ",
                email = " ada@example.com ",
                birthDateMillis = now,
                experience = "Intermediate",
                androidSkillSelected = false,
                uiUxSkillSelected = false,
                webSkillSelected = true,
                nowMillis = now
            )
        )
    }
}
