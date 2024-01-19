package com.cryptospark.common

import android.content.Intent
import android.net.Uri
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntentExtensionKtTest {

    @Test
    fun `Building a Url intent should return a valid Intent`() {
        // Given
        val url = "http://www.google.com"
        val urlUri = Uri.parse(url)

        // When
        val intent = com.cryptospark.common.buildUrlIntent(url)

        // Then
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(urlUri, intent.data)
    }

}
