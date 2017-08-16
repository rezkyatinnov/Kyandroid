package com.rezkyatinnov.kyandroid.localdata;

import com.rezkyatinnov.kyandroid.Kyandroid;
import com.rezkyatinnov.kyandroid.session.SessionNotFoundException;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by rezkyatinnov on 15/08/2017.
 */

public class LocalData {

    public static Realm getInstance(){
        if(Kyandroid.DB_KEY.isEmpty()) {
            return Realm.getDefaultInstance();
        }else{
            Kyandroid.DB_KEY.getBytes();
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .encryptionKey(Kyandroid.DB_KEY.getBytes())
                    .build();
            return Realm.getInstance(config);
        }
    }

    public static <O extends RealmObject> void saveOrUpdate(O object, OnTransactionCallback callback) {
        Realm realm = LocalData.getInstance();

        realm.executeTransactionAsync(
                realm1 -> realm1.copyToRealmOrUpdate(object),
                callback::onSuccess,
                callback::onError
        );
    }

    public static <O extends  RealmObject> O get(QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{
        Realm realm = LocalData.getInstance();
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        O result = query.findFirst();
        if(result != null){
            return result;
        }
        throw new SessionNotFoundException("queried data is not found");
    }

    public static <O extends RealmObject> List<O> getList(QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{

        Realm realm = LocalData.getInstance();
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        return realm.copyFromRealm(query.findAll());
    }

    public static abstract class OnTransactionCallback implements Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

        @Override
        public abstract void onError(Throwable error);

        @Override
        public abstract void onSuccess();
    }
}
