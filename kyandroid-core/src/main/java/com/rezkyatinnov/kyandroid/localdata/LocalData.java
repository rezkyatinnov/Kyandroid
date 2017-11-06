package com.rezkyatinnov.kyandroid.localdata;

import com.rezkyatinnov.kyandroid.Kyandroid;
import com.rezkyatinnov.kyandroid.session.SessionNotFoundException;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rezkyatinnov on 15/08/2017.
 */

public class LocalData {
    public static Realm getRealm(){
        if(Kyandroid.getDbKey().isEmpty()) {
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(Kyandroid.getSchemaName())
                    .schemaVersion(Kyandroid.getSchemaVersion())
                    .modules(Kyandroid.getRealmBasemodule(), Kyandroid.getRealmModule())
                    .build();
            return Realm.getInstance(config);
        }else{
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .encryptionKey(Kyandroid.getDbKey().getBytes())
                    .name(Kyandroid.getSchemaName())
                    .schemaVersion(Kyandroid.getSchemaVersion())
                    .modules(Kyandroid.getRealmBasemodule(), Kyandroid.getRealmModule())
                    .build();
            return Realm.getInstance(config);
        }
    }

    public static <O extends RealmObject> void saveOrUpdate(O object) {
        saveOrUpdate(LocalData.getRealm(),object);
    }

    public static <O extends RealmObject> void saveOrUpdate(Realm realmInstance, O object) {
        Realm realm = realmInstance;
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        realm.close();
    }

    public static <O extends RealmObject> void saveOrUpdate(O object, OnTransactionCallback callback) {
        saveOrUpdate(LocalData.getRealm(),object,callback);
    }

    public static <O extends RealmObject> void saveOrUpdate(Realm realmInstance,O object, OnTransactionCallback callback) {
        Realm realm = realmInstance;
        realm.executeTransactionAsync(
                realm1 -> realm1.copyToRealmOrUpdate(object),
                callback::onSuccess,
                callback::onError
        );
    }

    public static <O extends  RealmObject> O get(QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{
        return get(LocalData.getRealm(),filters,clazz);
    }

    public static <O extends  RealmObject> O get(Realm realmInstance, QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{
        Realm realm = realmInstance;
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        O result = query.findFirst();
        if(result != null){
            O finalResult = realm.copyFromRealm(result);
            realm.close();
            return finalResult;
        }
        realm.close();
        throw new SessionNotFoundException("queried data is not found");
    }

    public static <O extends  RealmObject> void delete(QueryFilters filters, Class<O> clazz){
        delete(LocalData.getRealm(),filters,clazz);
    }

    public static <O extends  RealmObject> void delete(Realm realmInstance, QueryFilters filters, Class<O> clazz){
        Realm realm = realmInstance;
        realm.beginTransaction();
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        final RealmResults<O> results = query.findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static <O extends RealmObject> List<O> getList(QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{
        return getList(LocalData.getRealm(),filters,clazz);
    }

    public static <O extends RealmObject> List<O> getList(Realm realmInstance, QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException{
        Realm realm = realmInstance;
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        List<O> results = realm.copyFromRealm(query.findAll());
        realm.close();
        return results;
    }

    public static abstract class OnTransactionCallback implements Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

        @Override
        public abstract void onError(Throwable error);

        @Override
        public abstract void onSuccess();
    }
}
