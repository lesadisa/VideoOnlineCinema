package com.lesadisa.videoonlinecinema.base

import android.content.Context
import com.lesadisa.videoonlinecinema.Const.HttpConst
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun formatDate(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val formatter = SimpleDateFormat("yyyy", Locale.ROOT)
    return try {
        formatter.format(parser.parse(date) ?: "")
    } catch (e: ParseException) {
        date
    }
}

fun httpCache10Mb(context: Context): Cache = Cache(
    directory = context.cacheDir,
    maxSize = HttpConst.CACHE_SIZE_10_MB
)

private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun okHttp(cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .addNetworkInterceptor(CacheControlInterceptor())
        .addInterceptor(loggingInterceptor())
        .build()
}