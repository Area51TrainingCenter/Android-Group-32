package com.jcodee.mod2class1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jcodee.mod2class1.modelos.CategoriaModelo;
import com.jcodee.mod2class1.modelos.NoticiaModelo;

import java.util.ArrayList;

/**
 * Created by johannfjs on 8/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class SentenciaSQL {
    private ManageHelper conexion;

    public SentenciaSQL(Context context) {
        conexion = new ManageHelper(context);
    }

    public ArrayList<CategoriaModelo> obtenerCategorias() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from categoria", null);
        ArrayList<CategoriaModelo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                CategoriaModelo item = new CategoriaModelo();
                item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                item.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                lista.add(item);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void registraNoticia(NoticiaModelo modelo) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_categoria", modelo.getIdCategoria());
        contentValues.put("titulo", modelo.getTitulo());
        contentValues.put("noticias", modelo.getNoticia());

        db.insert("noticia", null, contentValues);
    }

    public ArrayList<NoticiaModelo> obtenerNoticias() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from noticia a " +
                        "inner join categoria b on a.id_categoria=b.id",
                null);
        ArrayList<NoticiaModelo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                NoticiaModelo noticiaModelo = new NoticiaModelo();
                /*
                CategoriaModelo categoriaModelo=new CategoriaModelo();
                categoriaModelo.setId();
                categoriaModelo.setDescripcion();
                noticiaModelo.setCategoria(categoriaModelo);
                */
                noticiaModelo.setCategoria(cursor.getString(cursor.getColumnIndex("descripcion")));
                noticiaModelo.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                noticiaModelo.setNoticia(cursor.getString(cursor.getColumnIndex("noticias")));
                lista.add(noticiaModelo);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void eliminarNoticia(int idNoticia) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        db.delete("noticia", "id=" + idNoticia, null);
    }

    public void actualizarNoticia(NoticiaModelo modelo) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_categoria", modelo.getIdCategoria());
        contentValues.put("titulo", modelo.getTitulo());
        contentValues.put("noticias", modelo.getNoticia());
        db.update("noticia", contentValues, "id=" + modelo.getId(), null);
    }
}
