package com.rezkyatinnov.kyandroid.reztrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import retrofit2.Response;

/**
 * Created by rezkyatinnov on 23/10/2017.
 */

public abstract class RestObserver<T> implements Observer<Response<T>> {
    @Override
    public abstract void onSubscribe(Disposable d);

    @Override
    public void onNext(Response<T> response){

        if (response.isSuccessful()) {
            onSuccess(response.headers(),response.body());
        } else {
            ErrorResponse error = ErrorResponseUtil.parseError(response);
            onFailed(error);
        }
    }

    @Override
    public void onError(Throwable e){
            ErrorResponse errorRes = new ErrorResponse();
            errorRes.setMessage(e.getMessage());
            errorRes.setCode(e.hashCode());
            onFailed(errorRes);
    }

    @Override
    public abstract void onComplete();

    public abstract void onSuccess(Headers headers, T body);
    public abstract void onFailed(ErrorResponse error);
}
