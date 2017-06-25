package com.jcodee.temaservice;

import android.app.Application;
import android.content.IntentFilter;

/**
 * Created by johannfjs on 24/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter(CONNECTIVITY_SERVICE);
        registerReceiver(new InternetReceiver(), intentFilter);
    }
}
