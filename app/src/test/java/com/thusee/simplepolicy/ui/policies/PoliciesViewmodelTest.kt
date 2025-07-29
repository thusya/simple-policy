package com.thusee.simplepolicy.ui.policies

import com.thusee.simplepolicy.MainCoroutineExtension
import com.thusee.simplepolicy.domain.GetPoliciesUseCase
import com.thusee.simplepolicy.domain.model.Policy
import com.thusee.simplepolicy.ui.common.UIState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class PoliciesViewModelTest {
    private var mockGetPoliciesUseCase: GetPoliciesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: PoliciesViewmodel

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `fetchPolicies with non-empty list should emit Success`() = runTest {
        // Given
        val policy1 = Policy(
            policyId = "POL123",
            status = "Active",
            policyNumber = "PN123456",
            nextPremiumDate = "2025-08-15",
            startDate = "2020-08-15",
            maturityDate = "2030-08-15",
            sumAssured = "100000",
            premiumFrequency = "Monthly",
            lastPremiumPaidDate = "2025-07-15",
            nextPremiumAmount = "500"
        )
        val policy2 = Policy(
            policyId = "POL456",
            status = "Lapsed",
            policyNumber = "PN654321",
            nextPremiumDate = "2025-09-01",
            startDate = "2015-09-01",
            maturityDate = "2025-09-01",
            sumAssured = "200000",
            premiumFrequency = "Yearly",
            lastPremiumPaidDate = "2024-09-01",
            nextPremiumAmount = "2000"
        )
        val policies = listOf(policy1, policy2)
        coEvery { mockGetPoliciesUseCase() } returns policies

        // When
        viewModel = PoliciesViewmodel(mockGetPoliciesUseCase)
        advanceUntilIdle()

        // Then
        val state = viewModel.policyState.value
        assertTrue(state is UIState.Success)
        assertEquals(policies, (state as UIState.Success).data)
    }

    @Test
    fun `fetchPolicies with empty list should emit Empty`() = runTest {
        // Given
        coEvery { mockGetPoliciesUseCase() } returns emptyList()

        // When
        viewModel = PoliciesViewmodel(mockGetPoliciesUseCase)
        advanceUntilIdle()

        // Then
        assertEquals(UIState.Empty, viewModel.policyState.value)
    }

    @Test
    fun `fetchPolicies when use case throws should emit Error`() = runTest {
        // Given
        val exception = Exception("Network failure")
        coEvery { mockGetPoliciesUseCase() } throws exception

        // When
        viewModel = PoliciesViewmodel(mockGetPoliciesUseCase)
        advanceUntilIdle()

        // Then
        val state = viewModel.policyState.value
        assertTrue(state is UIState.Error)
    }
}

