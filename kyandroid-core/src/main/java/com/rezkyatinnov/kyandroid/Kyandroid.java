package com.rezkyatinnov.kyandroid;

import android.content.Context;
import com.rezkyatinnov.kyandroid.reztrofit.Reztrofit;
import com.rezkyatinnov.kyandroid.utils.SharedPrefUtils;

import io.realm.Realm;

/**
 * Created by rezkyatinnov on 09/08/2017.
 */

public class Kyandroid {

    private static String DB_KEY = "";
    private static String SCHEMA_NAME = "";
    private static long SCHEMA_VERSION = 1;
    private static Object[] REALM_MODULES;

    public static void init(Context context,String restBaseUrl, Class restService){
        Realm.init(context);
        Reztrofit.getInstance().init(context,restBaseUrl,restService);
        SharedPrefUtils.getInstance().init(context,SharedPrefUtils.PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public static void init(Context context,String restBaseUrl, Class restService, String sharedPrefName, int contextMode){
        Realm.init(context);
        Reztrofit.getInstance().init(context,restBaseUrl,restService);
        SharedPrefUtils.getInstance().init(context,sharedPrefName,contextMode);
    }

    public static void setDefaultRealmConfig(String schemaName, long schemaVersion, Object... moduleObjects){
        SCHEMA_NAME = schemaName;
        SCHEMA_VERSION = schemaVersion;
        REALM_MODULES = moduleObjects;
    }

    public static String getDbKey() {
        return DB_KEY;
    }

    public static String getSchemaName() {
        return SCHEMA_NAME;
    }

    public static long getSchemaVersion() {
        return SCHEMA_VERSION;
    }

    public static Object[] getRealmModule() {
        return REALM_MODULES;
    }

    public static void setDbKey(String dbKey) throws DbKeyWrongLengthException  {
        if(dbKey.length()==64) {
            DB_KEY = dbKey;
        } else {
            throw new DbKeyWrongLengthException("DB Key must be 64 length String");
        }
    }

    public static class DbKeyWrongLengthException extends Exception {

        public DbKeyWrongLengthException(String s) {
            super(s);
        }
    }
}
