package com.rezkyatinnov.kyandroid.session;

/**
 * Created by rezkya on 8/13/17.
 */

public interface SessionCallback {
    void onError(Throwable error);

    void onSuccess();
}
