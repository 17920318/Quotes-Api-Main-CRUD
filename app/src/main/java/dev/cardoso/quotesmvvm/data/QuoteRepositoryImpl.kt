package dev.cardoso.quotesmvvm.data

import android.accounts.NetworkErrorException
import android.util.Log
import dev.cardoso.quotesmvvm.core.convertToList
import dev.cardoso.quotesmvvm.data.local.QuoteLocalDataSource
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.remote.QuoteRemoteDataSource
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuoteRepositoryImpl @Inject constructor (private val localDataSource: QuoteLocalDataSource,
                                               private val remoteDataSource: QuoteRemoteDataSource): QuoteRepository {

    override suspend  fun getQuotes(): Flow<QuoteResponse?> {
        val remoteQuotes = remoteDataSource.getQuotes()

        val quotes = ArrayList<QuoteModel>()
        remoteQuotes.let { it->
            it.collect {
                it?.data?.forEach { quoteModel->
                    quotes.add(quoteModel)
                }
            }
            localDataSource.insertAll(quotes)
        }
        return (remoteQuotes)
    }

    override suspend fun getQuoteRandom(): Flow<QuoteModel> {
        return  localDataSource.getQuoteRandom()
    }

    override suspend fun getQuote(quoteId: Int): Flow<QuoteModel> {
        return localDataSource.getQuote(quoteId)
    }

    override suspend fun editQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return  remoteDataSource.editQuote(quoteModel, token)
    }

    override suspend fun addQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
            return remoteDataSource.addQuote(quoteModel, token)
        }

    override suspend fun deleteQuote(quoteId: Int, token: String): Flow<QuoteResponse?> {
        return remoteDataSource.deleteQuote(token, quoteId)
    }

    suspend fun getQuotesResponse(token: String): Flow<QuoteResponse?> {
        return remoteDataSource.getQuotesResponse(token = token)
    }
}
