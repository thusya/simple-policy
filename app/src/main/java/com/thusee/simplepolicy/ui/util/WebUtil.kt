package com.thusee.simplepolicy.ui.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri
import com.google.androidbrowserhelper.trusted.TwaLauncher
import com.thusee.simplepolicy.R

class WebUtil {
    fun launchCustomTab(context: Context, url: String) {
        val uri = url.toUri()

        try {
            val launcher = TwaLauncher(context)
            launcher.launch(
                uri
            )
        } catch (_: ActivityNotFoundException) {
            launchUriWithChooser(context, uri)
        }
    }

    private fun launchUriWithChooser(context: Context, uri: Uri) {
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        val chooserIntent = Intent.createChooser(
            browserIntent,
            context.getString(R.string.choose_your_browser)
        )
        try {
            context.startActivity(chooserIntent)
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(
                context,
                context.getString(R.string.no_browser_found),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}