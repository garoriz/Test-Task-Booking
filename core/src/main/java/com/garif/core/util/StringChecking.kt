package com.garif.core.util

import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPhoneNumberValid(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Pattern.compile("[+]7[0-9]{10}").matcher(this).matches()
}