package com.legalai.app.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class GeminiClient {

    // ⚠️ Replace with your actual Gemini API key from https://aistudio.google.com/app/apikey
    public static final String API_KEY = "YOUR_GEMINI_API_KEY_HERE";

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/";
    private static GeminiApiService instance;

    public static GeminiApiService getInstance() {
        if (instance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(GeminiApiService.class);
        }
        return instance;
    }

    public static boolean isApiKeySet() {
        return !API_KEY.equals("YOUR_GEMINI_API_KEY_HERE") && !API_KEY.isEmpty();
    }
}
