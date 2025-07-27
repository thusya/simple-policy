package com.thusee.simplepolicy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Policy(
    val policyId: String,
    val status: String,
    val policyNumber: String,
    val nextPremiumDate: String,
    val startDate: String? = null,
    val maturityDate: String? = null,
    val sumAssured: String? = null,
    val premiumFrequency: String? = null,
    val lastPremiumPaidDate: String? = null,
    val nextPremiumAmount: String? = null,
)