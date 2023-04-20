package com.eduardandroid.wembleymoviesapp.data.network

interface IPrintLogger {
	fun printError(error: String)
	fun printError(error: Throwable)
}