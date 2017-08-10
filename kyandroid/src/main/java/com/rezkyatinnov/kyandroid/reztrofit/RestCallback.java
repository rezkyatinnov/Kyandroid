package com.rezkyatinnov.kyandroid.reztrofit;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public abstract class RestCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.headers(),response.body());
        } else {
            ErrorResponse error = ErrorResponseUtil.parseError(response);
            onFailed(error);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(call.isCanceled()) {
            onCanceled();
        } else {
            ErrorResponse errorRes = new ErrorResponse();
            onFailed(errorRes);
        }
    }

    abstract void onSuccess(Headers headers, T body);
    abstract void onFailed(ErrorResponse error);
    abstract void onCanceled();
}
