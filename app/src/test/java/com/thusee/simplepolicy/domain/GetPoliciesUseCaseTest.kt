package com.thusee.simplepolicy.domain

import android.content.Context
import android.content.res.AssetManager
import com.thusee.simplepolicy.MainCoroutineExtension
import com.thusee.simplepolicy.domain.model.Policy
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class GetPoliciesUseCaseTest {
    private val mockAssets: AssetManager = mockk(relaxed = true)
    private val mockContext: Context = mockk<Context>(relaxed = true).apply {
        every { assets } returns this@GetPoliciesUseCaseTest.mockAssets
    }
    private val useCase = GetPoliciesUseCase(mockContext)

    @Test
    fun `invoke returns list of policies decoded from JSON`() = runTest {
        // Given
        val policies = listOf(
            Policy(
                policyId = "POL1",
                status = "Active",
                policyNumber = "PN1",
                nextPremiumDate = "2025-08-01"
            ),
            Policy(
                policyId = "POL2",
                status = "Lapsed",
                policyNumber = "PN2",
                nextPremiumDate = "2025-09-01"
            )
        )
        val jsonString = Json.encodeToString(policies)
        val inputStream = jsonString.byteInputStream()
        every { mockAssets.open("policies.json") } returns inputStream

        // When
        val result = useCase.invoke()

        // Then
        assertEquals(policies, result)
    }

    @Test
    fun `invoke throws IOException when asset open fails`() = runTest {
        // Given
        every { mockAssets.open("policies.json") } throws IOException("File missing")

        // Then
        assertFailsWith<IOException> {
            // When
            useCase.invoke()
        }
    }
}