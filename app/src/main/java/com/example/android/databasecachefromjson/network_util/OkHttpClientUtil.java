package com.example.android.databasecachefromjson.network_util;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Invocation;

public class OkHttpClientUtil {
    public static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
//                .addInterceptor(new InvocationLogger())
                .build();
    }

    static final class InvocationLogger implements Interceptor {
        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startNanos = System.nanoTime();
            Response response = chain.proceed(request);
            long elapsedNanos = System.nanoTime() - startNanos;

            Invocation invocation = request.tag(Invocation.class);
            if (invocation != null) {
                System.out.printf(
                        "%s.%s %s HTTP %s (%.0f ms)%n",
                        invocation.method().getDeclaringClass().getSimpleName(),
                        invocation.method().getName(),
                        invocation.arguments(),
                        response.code(),
                        elapsedNanos / 1_000_000.0);
            }
            return response;
        }
    }
}