package dev.cardoso.quotesmvvm.data

import android.util.Log
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.remote.QuoteApi
import dev.cardoso.quotesmvvm.data.remote.QuoteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Response
import javax.inject.Inject


class QuoteRemoteDataSourceImpl @Inject constructor(private  val api:QuoteApi): QuoteRemoteDataSource  {
    override suspend fun getQuotes(): Flow<QuoteResponse?> {
        return (responseToQuoteResponse(api.getQuotes()))
    }

    override suspend fun editQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return  responseToQuoteResponse(api.editQuote(token, quoteModel.id, quoteModel))
    }

    override suspend fun addQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return responseToQuoteResponse( api.addQuote(token, quoteModel))

    }
    override suspend fun deleteQuote(token: String, id: Int): Flow<QuoteResponse?> {
        return responseToQuoteResponse( api.deleteQuote(token = token , id=id))

    }

    override suspend fun getQuotesResponse(token: String): Flow<QuoteResponse?> {
        return responseToQuoteResponse(api.getQuotes())
    }

    private fun responseToQuoteResponse(response: Response<QuoteResponse>): Flow<QuoteResponse?> {
        return (when (response.isSuccessful) {
            true -> {
                response.body().let {
                    flow {
                        if (it != null) {
                            emit(it)
                        }
                    }
                }
            }
            else -> {
                Log.e("RESPUESTA-ERROR", response.errorBody().toString())
                val jsonObject =

                    JSONTokener(response.errorBody()?.string()).nextValue() as JSONObject
                val quoteResponse = QuoteResponse(
                    success = false,
                    message = jsonObject.
                    getString("message"), data = listOf()
                )
                flow { emit(quoteResponse) }
            }
        })

    }
    /*override suspend fun addQuote(token:String, quoteModel: QuoteModel): Flow<AddQuoteResponse>? {
        val response =  api.addQuote (token, quoteModel)

        return (when(response.isSuccessful){
            true-> {
                response.body().let{
                    flow{
                        if(it!= null){
                            emit(it)
                        }
                    }

                }
            }
            else ->{
                val jsonObject = JSONTokener(response.errorBody()?.string()).nextValue() as JSONObject
                val addQuoteResponse = AddQuoteResponse(
                    success = false,
                    message = jsonObject.getString("message"), data = QuoteModel(0,"","")
                )
                Log.e("ERROR", addQuoteResponse.toString())
                flow { emit(addQuoteResponse) }
            }
        }
                )
    }
}*/

}