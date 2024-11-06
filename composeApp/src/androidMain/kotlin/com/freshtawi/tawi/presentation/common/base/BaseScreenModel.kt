package com.freshtawi.tawi.presentation.common.base

import androidx.lifecycle.ViewModel
import com.freshtawi.tawi.domain.utils.ErrorState
import com.freshtawi.tawi.domain.utils.handelAuthorizationException
import com.freshtawi.tawi.domain.utils.handelInternetException
import com.freshtawi.tawi.domain.utils.AuthorizationException
import com.freshtawi.tawi.domain.utils.InternetException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent

abstract class BaseScreenModel<S, E>(initialState: S) : KoinComponent,ViewModel() {

    val viewModelScope: CoroutineScope
        get() {
            return CoroutineScope(Dispatchers.IO)
        }
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E?>()
    val effect = _effect.asSharedFlow().throttleFirst(500).mapNotNull { it }

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
    ): Job {
        return runWithErrorCheck(onError, inScope) {
            val result = function()
            onSuccess(result)
        }
    }

    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
    ): Job {
        return runWithErrorCheck(onError, inScope) {
            function().distinctUntilChanged().collectLatest {
                onNewValue(it)
            }
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }

    protected fun sendNewEffect(newEffect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(newEffect)
        }
    }

    private fun runWithErrorCheck(
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
        function: suspend () -> Unit,
    ): Job {
        return inScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: InternetException) {
                handelInternetException(exception, onError)
            } catch (exception: AuthorizationException) {
                handelAuthorizationException(exception, onError)
            } catch (e: AuthorizationException.LocationAccessDeniedException) {
                onError(ErrorState.LocationPermissionDenied)
            } catch (exception: Exception) {
                onError(ErrorState.RequestFailed)
            }
        }
    }

    private fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
        require(periodMillis > 0)
        return flow {
            var lastTime = 0L
            collect { value ->
                val currentTime = Clock.System.now().toEpochMilliseconds()
                if (currentTime - lastTime >= periodMillis) {
                    lastTime = currentTime
                    emit(value)
                }
            }
        }
    }

    protected fun launchDelayed(duration: Long, block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            delay(duration)
            block()
        }
    }
}
