package dev.cardoso.quotesmvvm.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cardoso.quotesmvvm.data.local.QuoteDB
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.domain.usecase.GetQuoteRandomUseCase
import dev.cardoso.quotesmvvm.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
@Inject constructor(private val getQuoteRandomUseCase: GetQuoteRandomUseCase,
                    private val getQuotesUseCase: GetQuotesUseCase
): ViewModel() {

    private val quoteModelMutableStateFlow = MutableStateFlow(listOf(QuoteModel(0,"","")))
    val listQuoteViewModel: StateFlow<List<QuoteModel>> = quoteModelMutableStateFlow

    private val quoteModelRandomMutableStateFlow = MutableStateFlow(QuoteModel(0,"",""))
    val quoteModel: StateFlow<QuoteModel> = quoteModelRandomMutableStateFlow

    fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase.getQuotes().collect {
                if (it != null) {
                    quoteModelMutableStateFlow.value=it.data
                }
            }
        }
    }
    //---  Load data from a suspend fun and mutate state
    fun randomQuote() {
        viewModelScope.launch {
            quoteModelRandomMutableStateFlow.value = getQuoteRandomUseCase.getQuoteRandom().first()
            //_quoteModel.value = GetQuoteUseCase(quoteDAO).getQuote(1).first()
        }
    }
}