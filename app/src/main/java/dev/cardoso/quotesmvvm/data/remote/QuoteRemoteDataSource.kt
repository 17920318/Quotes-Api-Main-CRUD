package dev.cardoso.quotesmvvm.data.remote

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow


interface QuoteRemoteDataSource {
    suspend  fun getQuotes(): Flow<QuoteResponse?>
    suspend  fun editQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?>
    suspend  fun addQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?>
    suspend fun deleteQuote(token: String, id: Int): Flow<QuoteResponse?>
    suspend fun getQuotesResponse(token: String): Flow<QuoteResponse?>

}