package com.jcodee.mod2class4.aplicacion;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by johannfjs on 6/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Shared Preferences
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("class4.realm")
                        .schemaVersion(1)
                        .initialData(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {

                            }
                        })
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
