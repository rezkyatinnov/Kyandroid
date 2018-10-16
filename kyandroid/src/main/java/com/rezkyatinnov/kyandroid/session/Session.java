package com.rezkyatinnov.kyandroid.session;

import com.rezkyatinnov.kyandroid.localdata.LocalData;
import com.rezkyatinnov.kyandroid.localdata.LocalDataNotFoundException;
import com.rezkyatinnov.kyandroid.localdata.QueryFilters;

import java.util.List;

/**
 * Created by rezkya on 8/13/17.
 */

public class Session {

    public static void save(SessionObject sessionObject){
        LocalData.saveOrUpdate(LocalData.getRealm(), sessionObject);
    }

    public static SessionObject get(String key) throws SessionNotFoundException {
        QueryFilters filter = new QueryFilters();
        filter.add(SessionObject.FIELD_KEY,key);
        try {
            return LocalData.get(LocalData.getRealm(), filter,SessionObject.class);
        } catch (LocalDataNotFoundException e) {
            e.printStackTrace();
            throw new SessionNotFoundException("SessionObject with key = '" + key +"' is not found");
        }
    }

    public static void remove(String key) {
        QueryFilters filter = new QueryFilters();
        filter.add(SessionObject.FIELD_KEY,key);
        LocalData.delete(LocalData.getRealm(), filter,SessionObject.class);
    }

    public static void clear() {
        LocalData.truncate(LocalData.getRealm(),SessionObject.class);
    }

    public static List<SessionObject> getRestHeaders() {
        QueryFilters filter = new QueryFilters();
        filter.add(SessionObject.FIELD_REST_HEADER,true);
        return LocalData.getList(LocalData.getRealm(), filter,SessionObject.class);
    }
}
