package com.jcodee.mod1class3;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jcodee.mod1class3.adapters.PeliculaAdapter;
import com.jcodee.mod1class3.modelos.Pelicula;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by johannfjs on 18/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ListadoActivity extends AppCompatActivity {
    private EditText pelicula;
    private Button buscar;
    private ListView lista;
    private FrameLayout cargando;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        pelicula = (EditText) findViewById(R.id.etBuscar);
        buscar = (Button) findViewById(R.id.btnBuscar);
        lista = (ListView) findViewById(R.id.lvLista);
        cargando = (FrameLayout) findViewById(R.id.flCargando);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cargando.setVisibility(View.GONE);
                lista.setVisibility(View.VISIBLE);

                //Creamos el adapter que vamos a cargar en nuestro listado
                PeliculaAdapter peliculaAdapter =
                        new PeliculaAdapter(ListadoActivity.this, MainActivity.listaDatos);
                //Cambiamos los datos a mostrar en nuestro listView
                lista.setAdapter(peliculaAdapter);
            }
        }, 3000);

        //Crea en segundo plano una ejecución
        /*TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                cargando.setVisibility(View.GONE);
                lista.setVisibility(View.VISIBLE);

                //Creamos el adapter que vamos a cargar en nuestro listado
                PeliculaAdapter peliculaAdapter =
                        new PeliculaAdapter(ListadoActivity.this, MainActivity.listaDatos);
                //Cambiamos los datos a mostrar en nuestro listView
                lista.setAdapter(peliculaAdapter);
            }
        };
        //Te espera 3 segundos y después de ese tiempo ejecuta todo lo que se encuentre
        //dentro del run
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);*/


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Obtenemos el objeto de la lista segun la posición
                Pelicula dato = MainActivity.listaDatos.get(position);

                Dialog dialog = new Dialog(ListadoActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item_mensaje);

                TextView tvTitulo = (TextView) dialog.findViewById(R.id.tvPelicula);
                TextView tvCritica = (TextView) dialog.findViewById(R.id.tvCritica);
                TextView tvEstado = (TextView) dialog.findViewById(R.id.tvEstado);

                tvTitulo.setText(dato.getNombre());
                tvCritica.setText(dato.getCritica());
                tvEstado.setText(dato.getEstado());

                dialog.show();
                /*
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                */

                /*
                //Contruimos un mensaje de tipo AlertBuilder
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                //Ponemos el titulo
                builder.setTitle(dato.getNombre());
                //Ponemos el mensaje
                builder.setMessage(dato.getCritica());
                //Creamos un boton de tipo positivo
                builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cerramos el mensaje
                        dialog.dismiss();
                    }
                });
                //Creamos el mensaje
                builder.create().show();
                */

            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog=new ProgressDialog(ListadoActivity.this);
                progressDialog.setMessage("Cargando");
                progressDialog.show();

                String valorBusqueda = pelicula.getText().toString();

                ArrayList<Pelicula> listaFiltrada = new ArrayList<Pelicula>();
                for (Pelicula item : MainActivity.listaDatos) {
                    if (item.getNombre().toUpperCase().contains(valorBusqueda.toUpperCase())) {
                        listaFiltrada.add(item);
                    }
                }
                PeliculaAdapter peliculaAdapter =
                        new PeliculaAdapter(ListadoActivity.this, listaFiltrada);
                lista.setAdapter(peliculaAdapter);

                progressDialog.dismiss();

            }
        });

        pelicula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String valorBusqueda = pelicula.getText().toString();

                ArrayList<Pelicula> listaFiltrada = new ArrayList<Pelicula>();
                for (Pelicula item : MainActivity.listaDatos) {
                    if (item.getNombre().toUpperCase().contains(valorBusqueda.toUpperCase())) {
                        listaFiltrada.add(item);
                    }
                }
                PeliculaAdapter peliculaAdapter =
                        new PeliculaAdapter(ListadoActivity.this, listaFiltrada);
                lista.setAdapter(peliculaAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
