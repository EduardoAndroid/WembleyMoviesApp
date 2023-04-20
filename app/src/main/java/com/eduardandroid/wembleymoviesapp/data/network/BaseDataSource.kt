package com.eduardandroid.wembleymoviesapp.data.network

import retrofit2.Response
import java.io.IOException

/*abstract class BaseDataSource<T>(
	//private val logger: IPrintLogger,
) {

	suspend fun getResult(): Lce<T> {
		try {
			val response = createCall()
			if (response.isSuccessful) {
				val body = response.body()
				if (body != null) return Lce.Content(body)
			}
			val errorMessage = response.errorBody()?.charStream()?.readText() ?: ""
			//logger.printError(errorMessage)
			return Lce.Error(
				ErrorWithCode(
					errorMessage,
					response.code() ?: 0
				)
			)
		} catch (e: Exception) {
			//logger.printError(Throwable(e))
			return Lce.Error(ErrorBase(e.message ?: e.toString()))
		}
	}
	@Throws(IOException::class)
	protected abstract suspend fun createCall(): Response<T>
}*/