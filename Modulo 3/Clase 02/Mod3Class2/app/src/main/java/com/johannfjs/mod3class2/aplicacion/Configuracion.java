package com.johannfjs.mod3class2.aplicacion;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by johannfjs on 27/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos la base de datos
        Realm.init(getApplicationContext());
        //Creamos la nueva configuración para la base de datos
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("mapas.realm")
                .schemaVersion(1)
                .build();
        //Cambiamos la configuración de la base de datos
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
