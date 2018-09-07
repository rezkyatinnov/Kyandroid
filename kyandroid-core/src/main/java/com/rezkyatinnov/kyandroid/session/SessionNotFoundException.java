package com.rezkyatinnov.kyandroid.session;

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

}
