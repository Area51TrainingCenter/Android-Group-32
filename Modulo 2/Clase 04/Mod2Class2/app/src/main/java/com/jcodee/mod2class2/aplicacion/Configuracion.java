package com.jcodee.mod2class2.aplicacion;

import android.app.Application;

import com.jcodee.mod2class2.entidades.TarjetaEntidad;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by johannfjs on 29/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("tienda.realm")
                .schemaVersion(1)
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        TarjetaEntidad tarjetaEntidad = new TarjetaEntidad();

                        realm.copyToRealm(tarjetaEntidad);
                    }
                })
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
