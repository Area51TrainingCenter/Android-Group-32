package com.jcodee.temapokemon.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by johannfjs on 17/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Configuracion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(getApplicationContext());

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("temapokemon.realm")
                .schemaVersion(1)
                /*
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                    }
                })*/
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
