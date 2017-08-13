package com.rezkyatinnov.kyandroid;

import android.content.Context;
import com.rezkyatinnov.kyandroid.reztrofit.Reztrofit;
import com.rezkyatinnov.kyandroid.utils.SharedPrefUtils;

import io.realm.Realm;

/**
 * Created by rezkyatinnov on 09/08/2017.
 */

public class Kyandroid {

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

}
