package com.rezkyatinnov.kyandroid.localdata;

import com.rezkyatinnov.kyandroid.Kyandroid;
import com.rezkyatinnov.kyandroid.session.SessionNotFoundException;

import java.util.List;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by rezkyatinnov on 15/08/2017.
 */

public class LocalData {
    public static Realm getRealm() {
        if (Kyandroid.getDbKey().isEmpty()) {
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(Kyandroid.getSchemaName())
                    .schemaVersion(Kyandroid.getSchemaVersion())
                    .modules(Kyandroid.getRealmBasemodule(), Kyandroid.getRealmModule())
                    .build();
            return Realm.getInstance(config);
        } else {
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
        saveOrUpdate(LocalData.getRealm(), object);
    }

    public static <O extends RealmObject> void saveOrUpdate(Realm realm, O object) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        realm.close();
    }

    public static <O extends RealmObject> void saveOrUpdate(O object, OnTransactionCallback callback) {
        saveOrUpdate(LocalData.getRealm(), object, callback);
    }

    public static <O extends RealmObject> void saveOrUpdate(Realm realm, O object, OnTransactionCallback callback) {
        realm.executeTransactionAsync(
                realm1 -> realm1.copyToRealmOrUpdate(object),
                callback,
                callback
        );
    }

    public static <O extends RealmObject> O get(QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException {
        return get(LocalData.getRealm(), filters, clazz);
    }

    public static <O extends RealmObject> O get(Realm realmInstance, QueryFilters filters, Class<O> clazz) throws LocalDataNotFoundException {
        RealmQuery<O> query = realmInstance.where(clazz);
        query = filters.copyToRealmQuery(query);
        O result = query.findFirst();
        if (result != null) {
            O finalResult = realmInstance.copyFromRealm(result);
            realmInstance.close();
            return finalResult;
        }
        realmInstance.close();
        throw new SessionNotFoundException("queried data is not found");
    }

    public static <O extends RealmObject> void delete(QueryFilters filters, Class<O> clazz) {
        delete(LocalData.getRealm(), filters, clazz);
    }

    public static <O extends RealmObject> void delete(Realm realm, QueryFilters filters, Class<O> clazz) {
        realm.beginTransaction();
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        final RealmResults<O> results = query.findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static <O extends RealmObject> void truncate(Realm realm, Class<O> clazz) {
        realm.beginTransaction();
        RealmQuery<O> query = realm.where(clazz);
        final RealmResults<O> results = query.findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static void clear() {
        clear(LocalData.getRealm());
    }

    public static <O extends RealmObject> void clear(Realm realm) {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

    public static <O extends RealmObject> List<O> getList(QueryFilters filters, Class<O> clazz) {
        return getList(LocalData.getRealm(), filters, clazz);
    }

    public static <O extends RealmObject> List<O> getList(QueryFilters filters, Class<O> clazz, String sortBy, Sort sort) {
        return getList(LocalData.getRealm(), filters, clazz, sortBy, sort);
    }

    public static <O extends RealmObject> List<O> getList(Realm realm, QueryFilters filters, Class<O> clazz) {
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        List<O> results = realm.copyFromRealm(query.findAll());
        realm.close();
        return results;
    }

    public static <O extends RealmObject> List<O> getList(Realm realm, QueryFilters filters, Class<O> clazz, String sortBy, Sort sort) {
        RealmQuery<O> query = realm.where(clazz);
        query = filters.copyToRealmQuery(query);
        query.sort(sortBy,sort);
        List<O> results = realm.copyFromRealm(query.findAllAsync());
        realm.close();
        return results;
    }

    public static abstract class OnTransactionCallback implements Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

        @Override
        public abstract void onError(@Nullable Throwable error);

        @Override
        public abstract void onSuccess();
    }
}
