package com.freshtawi.tawi.presentation.common.resources.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.freshtawi.tawi.R
import java.util.*


fun String.capitalizeFirstLetter(): String {
    return lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun String.titleCase():String = this.replaceFirstChar {
    if (it.isLowerCase()) {
        it.titlecase(Locale.getDefault())
    } else {
        it.toString()
    }
}


fun openInBrowser(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_VIEW)

    intent.data = Uri.parse(link)

    try {
        startActivity(context, intent, null)
    } catch (e: Exception) {
        val clipboard = ContextCompat.getSystemService(
            context,
            ClipboardManager::class.java
        ) as ClipboardManager

        clipboard.setPrimaryClip(ClipData.newPlainText("url", link))

        Toast
            .makeText(
                context,
                context.getString(R.string.core_designsystem_copy_to_clipboard),
                Toast.LENGTH_LONG
            )
            .show()
    }
}

fun sendEmail(
    context: Context,
    recipients: Array<String>,
    subject: String,
    body: String = "",
) {
    val intent = Intent(Intent.ACTION_SENDTO)

    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, recipients)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, body)

    try {
        startActivity(context, intent, null)
    } catch (e: Exception) {
        e.printStackTrace()
        Toast
            .makeText(
                context,
                context.getString(R.string.core_designsystem_not_found_email),
                Toast.LENGTH_LONG
            )
            .show()
    }
}