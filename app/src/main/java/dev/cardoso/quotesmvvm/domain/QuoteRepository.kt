package dev.cardoso.quotesmvvm.domain

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
        suspend fun getQuotes(): Flow<QuoteResponse?>
        suspend fun getQuoteRandom(): Flow<QuoteModel>
        suspend fun getQuote(quoteId:Int): Flow<QuoteModel>
        suspend fun editQuote(quoteModel: QuoteModel, token:String): Flow<QuoteResponse?>
        suspend fun addQuote(quoteModel: QuoteModel, token:String): Flow<QuoteResponse?>
        suspend fun deleteQuote(quoteId: Int, token:String): Flow<QuoteResponse?>
}