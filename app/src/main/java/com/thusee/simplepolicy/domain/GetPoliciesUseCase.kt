package com.thusee.simplepolicy.domain

import android.content.Context
import com.thusee.simplepolicy.domain.model.Policy
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetPoliciesUseCase @Inject constructor(
    private val context: Context
) {

    /**
     * Mock use case that returns a list of dummy policies.
     *
     * Defined as a suspend function to simulate fetching data from a network source.
     */
    suspend operator fun invoke(): List<Policy> {
        val json = context.assets.open("policies.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(json)
    }
}