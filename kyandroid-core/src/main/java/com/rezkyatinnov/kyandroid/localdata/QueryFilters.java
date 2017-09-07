package com.rezkyatinnov.kyandroid.localdata;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by rezkyatinnov on 16/08/2017.
 */

public class QueryFilters {
    private HashMap filters = new HashMap();

    public QueryFilters() {
    }

    public void add(String key, Boolean value) {
        filters.put(key, value);
    }

    public void add(String key, Date value) {
        filters.put(key, value);
    }

    public void add(String key, Float value) {
        filters.put(key, value);
    }

    public void add(String key, Double value) {
        filters.put(key, value);
    }

    public void add(String key, Short value) {
        filters.put(key, value);
    }

    public void add(String key, Integer value) {
        filters.put(key, value);
    }

    public void add(String key, Long value) {
        filters.put(key, value);
    }

    public void add(String key, Byte value) {
        filters.put(key, value);
    }

    public void add(String key, String value) {
        filters.put(key, value);
    }

    public void add(String key, byte[] value) {
        filters.put(key, value);
    }

    public <O extends RealmObject> RealmQuery<O> copyToRealmQuery(RealmQuery<O> query){

        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue() instanceof  Byte){
                query.equalTo((String) pair.getKey(), (Byte) pair.getValue());
            } else if(pair.getValue() instanceof  Boolean){
                query.equalTo((String) pair.getKey(), (Boolean) pair.getValue());
            } else if(pair.getValue() instanceof  Date){
                query.equalTo((String) pair.getKey(), (Date) pair.getValue());
            } else if(pair.getValue() instanceof  byte[]){
                query.equalTo((String) pair.getKey(), (byte[]) pair.getValue());
            } else if(pair.getValue() instanceof  Integer){
                query.equalTo((String) pair.getKey(), (Integer) pair.getValue());
            } else if(pair.getValue() instanceof  Short){
                query.equalTo((String) pair.getKey(), (Short) pair.getValue());
            } else if(pair.getValue() instanceof  Long){
                query.equalTo((String) pair.getKey(), (Long) pair.getValue());
            } else if(pair.getValue() instanceof  Float){
                query.equalTo((String) pair.getKey(), (Float) pair.getValue());
            } else if(pair.getValue() instanceof  Double){
                query.equalTo((String) pair.getKey(), (Double) pair.getValue());
            } else if(pair.getValue() instanceof  String){
                query.equalTo((String) pair.getKey(), (String) pair.getValue());
            }
            it.remove();
        }
        return query;
    }
}
