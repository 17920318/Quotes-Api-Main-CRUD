package dev.cardoso.quotesmvvm.domain.usecase


import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.QuoteRepositoryImpl
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase  @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun getQuotes(): Flow<QuoteResponse?> = quoteRepository.getQuotes()


    }


//getQuotes(token: String)









/*
class GetQuotesUseCase  @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun getQuotes(): Flow<QuoteResponse?> = quoteRepository.getQuotes()

}


class GetQuotesUseCase @Inject constructor (quoteDAO: QuoteDAO, var quoteRepositoryImpl: QuoteRepository) {

    suspend fun getQuotes(token: String): Flow<List<QuoteModel>> = quoteRepositoryImpl.getQuotes(token)
    suspend fun getRemoteQuotes(token:String):Flow<QuoteResponse>? {
        return quoteRepositoryImpl.getQuotesResponse(token)
    }
}*/