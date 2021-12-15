package dev.cardoso.quotesmvvm.data.remote


import dev.cardoso.quotesmvvm.core.API_PATH
import dev.cardoso.quotesmvvm.core.QUOTE_PATH
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import retrofit2.Response
import retrofit2.http.*

interface QuoteApi {
    @GET("$QUOTE_PATH")
    suspend fun getQuotes(): Response<QuoteResponse>
    suspend fun getQuote(quoteId: Int): Any


    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @PUT("$QUOTE_PATH/{id}")
    suspend fun editQuote(@Header ("Authorization") token:String,
                          @Path("id") id:Int,
                          @Body quoteModel: QuoteModel): Response<QuoteResponse>


    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @POST("$QUOTE_PATH")
    suspend fun addQuote(@Header ("Authorization") token:String,
                         @Body quoteModel: QuoteModel): Response<QuoteResponse>


    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @DELETE("$QUOTE_PATH/{id}")
    suspend fun  deleteQuote(
        @Header("Authorization") token:String,
        @Path("id") id:Int): Response<QuoteResponse>


    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @GET("$QUOTE_PATH")
    suspend fun getQuotesResponse(
        @Header("Authorization") token:String): Response<QuoteResponse>
}

