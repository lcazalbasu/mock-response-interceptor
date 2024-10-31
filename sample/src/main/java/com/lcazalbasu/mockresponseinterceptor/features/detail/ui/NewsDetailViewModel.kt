package com.lcazalbasu.mockresponseinterceptor.features.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcazalbasu.mockresponseinterceptor.features.detail.usecases.LoadNewsDetailUseCase
import com.lcazalbasu.mockresponseinterceptor.navigation.NEWS_ID_ARG
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import com.lcazalbasu.mockresponseinterceptor.utils.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val loadNewsDetailUseCase: LoadNewsDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val newsId = savedStateHandle.get<Long>(NEWS_ID_ARG)

    private val _state = MutableStateFlow(NewsDetailState.default())
    val state: StateFlow<NewsDetailState> get() = _state

    init {
        load()
    }

    private fun load() = launchIO {
        if (newsId == null) {
            return@launchIO
        }

        viewModelScope.launch(Dispatchers.IO) {
            _state.update { _state.value.copy(isLoading = true) }
            when (val resource = loadNewsDetailUseCase.run(newsId)) {
                is Resource.Error -> _state.update {
                    _state.value.copy(exception = resource.failure)
                }

                is Resource.Success -> _state.update {
                    _state.value.copy(
                        detail = resource.data,
                        exception = null,
                    )
                }
            }
            _state.update { _state.value.copy(isLoading = false) }
        }
    }

    fun onErrorDialogClosed() = launchIO {
        _state.update { _state.value.copy(exception = null) }
    }
}
