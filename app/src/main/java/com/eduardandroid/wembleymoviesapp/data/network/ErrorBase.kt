package com.eduardandroid.wembleymoviesapp.data.network

// Error Connection
open class ErrorBase(
	val message: String
) {
	constructor() : this("")
}

// Not successful response
class ErrorWithCode(
	message: String,
	val code: Int,
) : ErrorBase(message) {
	constructor() : this("", 0)
}