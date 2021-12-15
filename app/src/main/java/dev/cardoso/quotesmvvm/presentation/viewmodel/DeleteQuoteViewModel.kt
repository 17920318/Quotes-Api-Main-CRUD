package dev.cardoso.quotesmvvm.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.usecase.DeleteQuoteUseCase
import dev.cardoso.quotesmvvm.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteQuoteViewModel @Inject constructor(private val deleteQuoteUseCase: DeleteQuoteUseCase) :
    ViewModel() {

    private val quoteResponseMutableStateFlow = MutableStateFlow(
        QuoteResponse(false, message = "", data = listOf())
    )
    val quoteResponse: StateFlow<QuoteResponse> = quoteResponseMutableStateFlow

    fun deleteQuote(token: String, id: Int) {
        viewModelScope.launch {
            deleteQuoteUseCase.deleteQuote(id, token)?.collect {
                if (it != null) {
                    quoteResponseMutableStateFlow.value = it
                }
            }
        }
    }
}