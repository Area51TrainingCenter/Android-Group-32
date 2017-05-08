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

    //Método para eliminar con solo tener el id
    public static void delete(int idTarjeta) {
        Realm realm = Realm.getDefaultInstance();
        //Consultar a la tabla que registro tiene el id que estamos obteniendo
        TarjetaEntidad result = realm.where(TarjetaEntidad.class)
                .equalTo("id", idTarjeta)
                .findFirst();
        //Iniciamos una transacción
        realm.beginTransaction();
        //Eliminamos el registro que se ha traído de la base de datos
        result.deleteFromRealm();
        //Guardamos los cambios
        realm.commitTransaction();
        //Cerramos la conexión
        realm.close();
    }

    public static long nextId() {
        Realm realm = Realm.getDefaultInstance();
        Number result = realm.where(TarjetaEntidad.class).max("id");
        return (result != null ? result.intValue() : 0) + 1;
    }

    public static TarjetaEntidad obtenerTarjetaPorId(int idTarjeta) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(TarjetaEntidad.class)
                .equalTo("id", idTarjeta)
                .findFirst();
    }
}
