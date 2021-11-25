package com.lesadisa.videoonlinecinema.base

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

@SuppressLint("InlinedApi")
fun hideSystemUI(window: Window, view: View) {
    Log.d("URLxxx", "hideSystemUI")
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, view).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@SuppressLint("InlinedApi")
fun showSystemUI(window: Window, view: View) {
    Log.d("URLxxx", "showSystemUI")
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, view)
        .show(WindowInsetsCompat.Type.systemBars())
}