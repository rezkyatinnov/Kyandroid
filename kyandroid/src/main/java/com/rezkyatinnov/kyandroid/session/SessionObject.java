package com.rezkyatinnov.kyandroid.session;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rezkya on 8/13/17.
 */

public class SessionObject extends RealmObject {
    public static String FIELD_KEY = "key";
    public static String FIELD_VALUE = "value";
    public static String FIELD_REST_HEADER = "restHeader";

    @PrimaryKey
    private String key;
    private String value;
    private boolean restHeader;

    public SessionObject(){}
    public SessionObject(String key, String value) {
        this.key = key;
        this.value = value;
        this.restHeader = false;
    }

    public SessionObject(String key, String value, boolean restHeader) {
        this.key = key;
        this.value = value;
        this.restHeader = restHeader;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRestHeader() {
        return restHeader;
    }

    public void setRestHeader(boolean restHeader) {
        this.restHeader = restHeader;
    }
}
