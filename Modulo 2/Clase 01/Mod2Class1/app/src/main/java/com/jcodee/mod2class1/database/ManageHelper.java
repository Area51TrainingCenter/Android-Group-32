package com.jcodee.mod2class1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by johannfjs on 8/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ManageHelper extends SQLiteOpenHelper {
    public ManageHelper(Context context) {
        super(context, "mod2class1.db", null, 1);
    }

    //Se crea por primera vez la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table categoria (id integer primary key autoincrement," +
                        "descripcion varchar(100))"
        );
        db.execSQL(
                "create table noticia (id integer primary key autoincrement," +
                        "id_categoria integer," +
                        "titulo varchar(100)," +
                        "noticias varchar(400))"
        );
        db.execSQL(
                "insert into categoria (descripcion) values ('Turismo')"
        );
        db.execSQL(
                "insert into categoria (descripcion) values ('Deporte')"
        );
    }

    //Para las siguientes versiones o actualizaciones que tendría la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            //Ejecutamos sentencia SQL si la versión de la base de datos es mayor a 1
        }
    }
}
