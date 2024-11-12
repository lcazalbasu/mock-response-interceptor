package com.lcazalbasu.mockresponseinterceptor.features.news.ui

import androidx.lifecycle.ViewModel
import com.lcazalbasu.mockresponseinterceptor.features.news.usecases.LoadNewsListUseCase
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import com.lcazalbasu.mockresponseinterceptor.utils.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val loadNewsListUseCase: LoadNewsListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsListState.default())
    val state: StateFlow<NewsListState> get() = _state

    init {
        loadNews()
    }

    private fun loadNews() = launchIO {
        _state.update { _state.value.copy(isLoading = true) }
        val resource = loadNewsListUseCase.run()
        delay(1000)
        when (resource) {
            is Resource.Error ->
                _state.update {
                    _state.value.copy(
                        exception = resource.failure,
                        list = emptyList(),
                    )
                }

            is Resource.Success -> _state.update {
                state.value.copy(
                    list = resource.data,
                    exception = null,
                )
            }
        }
        _state.update { _state.value.copy(isLoading = false) }
    }

    fun onErrorDialogClosed() = launchIO {
        _state.update { _state.value.copy(exception = null) }
    }

    fun refreshList() {
        loadNews()
    }
}
