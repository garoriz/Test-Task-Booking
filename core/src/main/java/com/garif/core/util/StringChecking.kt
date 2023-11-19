package com.garif.core.util

import android.text.TextUtils
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhoneNumber(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Pattern.compile("[+]7 [(][0-9]{3}[)] [0-9]{3}-[0-9]{2}-[0-9]{2}").matcher(this)
                .matches()
}

