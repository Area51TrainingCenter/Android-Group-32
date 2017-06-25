package com.jcodee.temapokemon.database;

import com.jcodee.temapokemon.entidades.GeneracionEntidad;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 24/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class MetodoRealm {
    public static void insertarGeneraciones(GeneracionEntidad entidad) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(entidad);
        realm.commitTransaction();
    }

    public static long obtenerCantidadGeneracion() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(GeneracionEntidad.class).count();
    }

    public static GeneracionEntidad obtenerGeneracion() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(GeneracionEntidad.class).findFirst();
    }

    public static void eliminarGeneracion() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(GeneracionEntidad.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
}
