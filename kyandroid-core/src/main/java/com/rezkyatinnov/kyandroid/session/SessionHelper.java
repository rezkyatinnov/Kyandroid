package com.rezkyatinnov.kyandroid.session;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rezkya on 8/13/17.
 */

public class SessionHelper {

    public static void saveSession(Session session, SessionCallback callback){
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(
                realm1 -> {
                    realm1.copyToRealmOrUpdate(session);
                },
                callback::onSuccess,
                callback::onError
        );
    }

    public static Session getSession(String key) throws SessionNotFoundException {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Session> query = realm.where(Session.class);
        query.equalTo(Session.FIELD_KEY, key);
        RealmResults<Session> result = query.findAll();
        for(Session session:result){
            return session;
        }
        throw new SessionNotFoundException("Session with key = '" + key +"' is not found");
    }

    public static RealmResults<Session> getRestHeaderSession() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Session> query = realm.where(Session.class);
        query.equalTo(Session.FIELD_REST_HEADER, true);
        RealmResults<Session> result = query.findAll();
        return result;
    }
}
