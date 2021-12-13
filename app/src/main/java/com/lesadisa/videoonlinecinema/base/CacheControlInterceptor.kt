package com.lesadisa.videoonlinecinema.base

import com.lesadisa.videoonlinecinema.Const.HttpConst.CACHE_CONTROL_HEADER
import com.lesadisa.videoonlinecinema.Const.HttpConst.CACHE_CONTROL_NO_CACHE
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheControlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
        if (!shouldUseCache) return originalResponse
// исменил с 10 минут на 3600
        val cacheControl = CacheControl.Builder()
            .maxAge(3600, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}