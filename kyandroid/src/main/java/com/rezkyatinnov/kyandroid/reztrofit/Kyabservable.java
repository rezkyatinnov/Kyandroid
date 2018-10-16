package com.rezkyatinnov.kyandroid.reztrofit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Response;

/**
 * Created by rezkyatinnov on 23/10/2017.
 */

public abstract class Kyabservable<T> extends Observable<Response<T>> {
}
