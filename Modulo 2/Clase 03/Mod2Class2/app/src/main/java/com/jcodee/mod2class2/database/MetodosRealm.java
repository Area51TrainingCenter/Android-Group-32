package com.jcodee.mod2class2.database;

import com.jcodee.mod2class2.entidades.TarjetaEntidad;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 29/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

//UUID
public class MetodosRealm {
    public static void registrarOActualizar(TarjetaEntidad tarjetaEntidad) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(tarjetaEntidad);
        realm.commitTransaction();
        realm.close();
    }

    public static RealmResults<TarjetaEntidad> listarTodo() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(TarjetaEntidad.class)
                //.equalTo("numero","1234567890")
                .findAll();
    }

    public static void delete(TarjetaEntidad tarjetaEntidad) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        tarjetaEntidad.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static long nextId() {
        Realm realm = Realm.getDefaultInstance();
        long result = realm.where(TarjetaEntidad.class).count();
        return result + 1;
    }
}
