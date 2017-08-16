package com.rezkyatinnov.kyandroid.session;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.rezkyatinnov.kyandroid.localdata.LocalDataNotFoundException;

/**
 * Created by rezkya on 8/13/17.
 */

public class SessionNotFoundException extends LocalDataNotFoundException {
    public SessionNotFoundException() {
    }

    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionNotFoundException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public SessionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
