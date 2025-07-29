package com.thusee.simplepolicy.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.simplepolicy.ui.util.WebUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val webUtil: WebUtil) : ViewModel() {

    private val _notificationEnabled = MutableStateFlow(true)
    val notificationEnabled: StateFlow<Boolean> = _notificationEnabled

    fun openWebView(url: String, context: Context) {
        webUtil.launchCustomTab(context, url)
    }

    fun onCheckedChange(checked: Boolean) {
        viewModelScope.launch {
            _notificationEnabled.value = checked
        }
    }
}