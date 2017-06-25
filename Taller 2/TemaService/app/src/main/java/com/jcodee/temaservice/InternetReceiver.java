package com.jcodee.temaservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by johannfjs on 24/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(context, "Si hay Internet", Toast.LENGTH_SHORT).show();

            Intent intentDemo = new Intent(context, MainActivity.class);
            PendingIntent pendingIntentDemo =
                    PendingIntent.getActivity(context, 0,
                            intentDemo, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //creamos la notificación con el titulo y mensaje a mostrar
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setContentTitle("Estado de Red")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("Hay internet")
                    .addAction(R.mipmap.ic_launcher, "Hola", pendingIntentDemo)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSound(uri);

            //Creamos el intent que se va a ejecutar una vez que se seleccione la notificción
            Intent intentNotification = new Intent(context, MainActivity.class);
            intentNotification.putExtra("id", "123");
            //Generamos un intent de espera según se realice el llamado
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0,
                            intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);
            //Seteamos el valor a realizar cuando se seleccione la notificacion
            builder.setContentIntent(pendingIntent);

            //Creamos el controlador de notificaciones
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //Ejecutamos la notificación que se realizara para poder cargarla
            notificationManager.notify(0, builder.build());

        } else {
            Toast.makeText(context, "No hay internet", Toast.LENGTH_SHORT).show();
        }
    }
}
