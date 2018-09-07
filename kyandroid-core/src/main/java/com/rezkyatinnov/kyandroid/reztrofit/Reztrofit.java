package com.rezkyatinnov.kyandroid.reztrofit;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rezkyatinnov.kyandroid.session.Session;
import com.rezkyatinnov.kyandroid.session.SessionObject;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public class Reztrofit<T> {
    private Context context;

    private T endpoint;
    private Retrofit retrofit;
    private List<Interceptor> interceptors;
    private Map<String, String> headers;
    @SuppressLint("StaticFieldLeak")
    private static final Reztrofit instance = new Reztrofit();
    private OkHttpClient client = new OkHttpClient();

    public static Reztrofit getInstance() {
        return instance;
    }

    private Reztrofit() {
        headers = new HashMap<>();
        interceptors = new ArrayList<>();
    }

    public void init(Context context, String baseUrl, Class<T> interfaceClass, Boolean enableRx) {
        this.context = context;
        Class<T> interfaceClass1 = interfaceClass;

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .readTimeout(2 * 60, TimeUnit.SECONDS)
                .connectTimeout(2 * 60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();

                    List<SessionObject> sessionObjects;
                    sessionObjects = Session.getRestHeaders();

                    for (SessionObject session : sessionObjects) {
                        builder.addHeader(session.getKey(), session.getValue());
                    }

                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        builder.addHeader(entry.getKey(), entry.getValue());
                    }
                    return chain.proceed(builder.build());
                })
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();

        if (enableRx) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .baseUrl(baseUrl)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .baseUrl(baseUrl)
                    .build();
        }

        endpoint = retrofit.create(interfaceClass1);
    }

    public T getEndpoint() {
        return endpoint;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void clearInterceptors() {
        interceptors.clear();
    }

    public void clearHeaders() {
        headers.clear();
    }

    public Context getContext() {
        return context;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void cancelAllRequest() {
        client.dispatcher().cancelAll();
    }
}
