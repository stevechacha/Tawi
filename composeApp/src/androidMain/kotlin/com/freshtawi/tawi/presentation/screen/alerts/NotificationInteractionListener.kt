package com.freshtawi.tawi.presentation.screen.alerts

import com.freshtawi.tawi.presentation.common.base.BaseInteractionListener


interface NotificationInteractionListener : BaseInteractionListener {
    fun onClickTrackOrder()
    fun onClickTryAgain()
    fun onClickLogin()
}