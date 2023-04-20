package com.eduardandroid.wembleymoviesapp.data.network

sealed class Resource<out T> {

	data class Success<out T>(val data: T) : Resource<T>()

	data class Error(val message: String, val throwable: Throwable?) : Resource<Nothing>()

	object Loading : Resource<Nothing>()

	companion object {

		fun <T> success(data: T): Resource<T> = Success(data)

		fun error(message: String, throwable: Throwable?): Resource<Nothing> = Error(message, throwable)

		fun loading(): Resource<Nothing> = Loading

	}

}

/*sealed class Lce<T> {
	data class Loading<T>(val TAG: String) : Lce<T>()
	data class Content<T>(val packet: T) : Lce<T>()
	data class Error<T>(val error: ErrorBase) : Lce<T>()

	override fun toString(): String {
		return when (this) {
			is Loading -> "Loading on $TAG"
			is Content -> "Content[data=$packet]"
			is Error -> "Error=$error]"
		}
	}

	fun getLce() : String {
		return when(this){
			is Loading -> "Loading"
			is Content -> "Content"
			is Error -> "Error"
		}
	}
}*/