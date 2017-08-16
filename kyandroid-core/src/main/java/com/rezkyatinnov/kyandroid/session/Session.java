package com.rezkyatinnov.kyandroid.session;

import com.rezkyatinnov.kyandroid.localdata.LocalData;
import com.rezkyatinnov.kyandroid.localdata.LocalDataNotFoundException;
import com.rezkyatinnov.kyandroid.localdata.QueryFilters;

import java.util.List;

/**
 * Created by rezkya on 8/13/17.
 */

public class Session {

    public static void save(SessionObject sessionObject, LocalData.OnTransactionCallback callback){
        LocalData.saveOrUpdate(sessionObject,callback);
    }

    public static SessionObject get(String key) throws SessionNotFoundException {
        QueryFilters filter = new QueryFilters();
        filter.add(SessionObject.FIELD_KEY,key);
        try {
            SessionObject result = LocalData.get(filter,SessionObject.class);
            return result;
        } catch (LocalDataNotFoundException e) {
            e.printStackTrace();
            throw new SessionNotFoundException("SessionObject with key = '" + key +"' is not found");
        }
    }

    public static List<SessionObject> getRestHeaders() throws SessionNotFoundException {
        QueryFilters filter = new QueryFilters();
        filter.add(SessionObject.FIELD_REST_HEADER,true);
        try {
            return LocalData.getList(filter,SessionObject.class);
        } catch (LocalDataNotFoundException e) {
            e.printStackTrace();
            throw new SessionNotFoundException("SessionObject with "+SessionObject.FIELD_REST_HEADER+" = true is not found");
        }
    }
}
