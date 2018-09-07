package com.rezkyatinnov.kyandroid.localdata;

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

}
