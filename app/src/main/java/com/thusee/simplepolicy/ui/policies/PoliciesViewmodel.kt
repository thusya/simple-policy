package com.thusee.simplepolicy.ui.policies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.simplepolicy.domain.GetPoliciesUseCase
import com.thusee.simplepolicy.domain.model.Policy
import com.thusee.simplepolicy.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoliciesViewmodel @Inject constructor(
    private val getPoliciesUseCase: GetPoliciesUseCase,
) : ViewModel() {

    private val _policyState = MutableStateFlow<UIState<List<Policy>, String>>(UIState.Loading)
    val policyState: StateFlow<UIState<List<Policy>, String>> = _policyState

    init {
        fetchPolicies()
    }

    private fun fetchPolicies() {
        viewModelScope.launch {
            _policyState.value = UIState.Loading
            try {
                val result = getPoliciesUseCase()

                if (result.isEmpty()) {
                    _policyState.value = UIState.Empty
                } else {
                    _policyState.value = UIState.Success(result)
                }
            } catch (e: Exception) {
                _policyState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}