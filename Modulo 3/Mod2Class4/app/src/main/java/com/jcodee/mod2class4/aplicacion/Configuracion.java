package com.jcodee.mod2class4.aplicacion;

import android.app.Application;

import com.jcodee.mod2class4.entidad.CicloEntidad;
import com.jcodee.mod2class4.entidad.ModuloEntidad;

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

                                ModuloEntidad moduloEntidad = new ModuloEntidad();
                                moduloEntidad.setId(1);
                                moduloEntidad.setDescripcion("Modulo 1");
                                realm.copyToRealm(moduloEntidad);

                                ModuloEntidad moduloEntidad1 = new ModuloEntidad();
                                moduloEntidad1.setId(2);
                                moduloEntidad1.setDescripcion("Modulo 2");
                                realm.copyToRealm(moduloEntidad1);

                                CicloEntidad cicloEntidad=new CicloEntidad();
                                cicloEntidad.setId(1);
                                cicloEntidad.setDescripcion("1er Ciclo");
                                realm.copyToRealm(cicloEntidad);

                                CicloEntidad cicloEntidad1=new CicloEntidad();
                                cicloEntidad1.setId(2);
                                cicloEntidad1.setDescripcion("2do Ciclo");
                                realm.copyToRealm(cicloEntidad1);

                            }
                        })
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
