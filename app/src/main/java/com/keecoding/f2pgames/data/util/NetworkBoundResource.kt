package com.keecoding.f2pgames.data.util

import android.util.Log
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline isFetchSuccess: () -> Boolean = { true },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            val fetchData = fetch()
            if (isFetchSuccess()) {
                saveFetchResult(fetchData)
                Log.d("aaa", "networkBoundResource: afeaesf")
                query().map { Resource.Success(it) }
            } else {
                Log.d("ddddd", "networkBoundResource: ")
                query().map { Resource.Error("Failed to Get Data From API", it) }
            }
        } catch (throwable: Throwable) {
            Log.d("aaa", "networkBoundResource: iuiuiiuuib" + throwable.message)
            query().map { Resource.Error(throwable.message ?: "", it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}