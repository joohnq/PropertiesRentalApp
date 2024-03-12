package com.joohnq.propertiesrentalapp.util

object TextInputVerification {
    fun verifyEmail(email: String, onEmailError: (Boolean, String) -> Unit): Boolean {
        if (email.isEmpty()) {
            onEmailError(true, "Email is required")
            return false
        }

        if (!email.contains("@")) {
            onEmailError(true, "Invalid email")
            return false
        }

        return true
    }

    fun verifyPassword(
        password: String,
        origin: Origin,
        onPasswordError: (Boolean, String) -> Unit
    ): Boolean {
        if (password.isEmpty()) {
            onPasswordError(true, "Password is required")
            return false
        }

        if (origin == Origin.REGISTER) {
            if (password.length < 6) {
                onPasswordError(true, "Password must have at least 6 characters")
                return false
            }
        }
        return true
    }

    fun verifyFields(
        email: String,
        password: String,
        origin: Origin,
        onEmailError: (Boolean, String) -> Unit,
        onPasswordError: (Boolean, String) -> Unit
    ): Boolean =
        verifyEmail(email, onEmailError) && verifyPassword(password, origin, onPasswordError)
}