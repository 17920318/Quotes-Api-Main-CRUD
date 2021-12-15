package dev.cardoso.quotesmvvm.core

import dev.cardoso.quotesmvvm.data.local.entities.QuoteEntity
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import kotlinx.coroutines.flow.*

suspend fun <T> Flow<List<T>>.convertToList() =
    flatMapConcat { it.asFlow() }.toList()

fun QuoteEntity.toQuoteModel() = QuoteModel(
    id =id,
    quote = quote,
    author = author,
)
fun QuoteModel.toEntity() = QuoteEntity(
    id=id,
    quote = quote,
    author = author,
)

fun List<QuoteModel>.toListQuoteEntity () =
    map {it.toEntity() }

fun List<QuoteEntity>.toListQuoteModel () =
    map {it.toQuoteModel() }
