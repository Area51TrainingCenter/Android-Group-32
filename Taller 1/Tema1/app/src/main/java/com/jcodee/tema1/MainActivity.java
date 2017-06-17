package com.jcodee.tema1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private Button btnCapturar;
    private ImageView ivImagen;

    private static int CODIGO_CAMARA = 438;
    private Uri rutaDeSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapturar = (Button) findViewById(R.id.btnCapturar);
        ivImagen = (ImageView) findViewById(R.id.ivImagen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Armamos la ruta de la carpeta donde se guardará el archivo
                String archivo =
                        Environment
                                .getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_PICTURES) + "/tema1/";
                //Creamos una variable de tipo File para poder mandar la ruta que hemos creado
                File file = new File(archivo);
                if (!file.exists()) {
                    //Creamos la ruta
                    file.mkdirs();
                }
                //Armamos la ruta de la foto donde estará
                //String foto = archivo + UUID.randomUUID() + ".jpg";

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Calendar calendar = Calendar.getInstance();

                int valor = file.listFiles().length;

                String foto = archivo + "IMG" +
                        dateFormat.format(calendar.getTimeInMillis()) +
                        StringUtils.leftPad(String.valueOf(valor + 1), 3, "0") + ".jpg";


                //Creamos una variable de tipo File para poder mandar la ruta de la foto
                File fileFoto = new File(foto);
                //Creamos el archivo de la foto
                try {
                    fileFoto.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Mapeamos la ruta
                rutaDeSalida = Uri.fromFile(fileFoto);

                //Llamamos a la camara
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Agregamos la ruta donde se guardará la foto
                intent.putExtra(MediaStore.EXTRA_OUTPUT, rutaDeSalida);
                //Llamamos a la camara para que podamos tomar una foto
                startActivityForResult(intent, CODIGO_CAMARA);
            }
        });
    }

    //Obtenemos la respuesta que nos arroja la camara (en este caso una foto)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Validamos que la respuesta que obtenemos sea del mismo código que hemos enviado
        if (requestCode == CODIGO_CAMARA && resultCode == RESULT_OK) {
            //Obtenemos los datos para poder mostrarlo
            //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //ivImagen.setImageBitmap(bitmap);
            ivImagen.setImageURI(rutaDeSalida);
        }
    }
}
