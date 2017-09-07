package com.rezkyatinnov.kyandroid.localdata;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by rezkyatinnov on 16/08/2017.
 */

public class LocalDataNotFoundException extends Exception {
    public LocalDataNotFoundException() {
    }

    public LocalDataNotFoundException(String s) {
        super(s);
    }

    public LocalDataNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public LocalDataNotFoundException(Throwable throwable) {
        super(throwable);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LocalDataNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
