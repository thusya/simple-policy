package com.thusee.simplepolicy.ui.profile

import android.content.Context
import com.thusee.simplepolicy.MainCoroutineExtension
import com.thusee.simplepolicy.ui.util.WebUtil
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class ProfileViewModelTest {
    private val mockWebUtil: WebUtil = mockk(relaxed = true)
    private lateinit var profileViewModel: ProfileViewModel

    @BeforeEach
    fun setUp() {
        profileViewModel = ProfileViewModel(mockWebUtil)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `onCheckedChange to false should update notificationEnabled to false`() = runTest {
        profileViewModel.onCheckedChange(false)

        assertEquals(false, profileViewModel.notificationEnabled.value)
    }

    @Test
    fun `onCheckedChange to true should update notificationEnabled to true`() = runTest {
        profileViewModel.onCheckedChange(true)

        assertEquals(true, profileViewModel.notificationEnabled.value)
    }

    @Test
    fun `openWebView should call WebUtil's launchCustomTab with correct context and URL`() =
        runTest {
            val url = "https://test.com"
            val context: Context = mockk(relaxed = true)

            profileViewModel.openWebView(url, context)

            verify { mockWebUtil.launchCustomTab(context, url) }
        }
}