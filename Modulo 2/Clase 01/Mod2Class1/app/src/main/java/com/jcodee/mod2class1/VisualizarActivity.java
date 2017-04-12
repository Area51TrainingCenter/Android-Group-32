package com.jcodee.mod2class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.jcodee.mod2class1.adapters.NoticiaAdapter;
import com.jcodee.mod2class1.database.SentenciaSQL;

public class VisualizarActivity extends AppCompatActivity {

    private ListView lvLista;
    private NoticiaAdapter adapter;
    private SentenciaSQL sentenciaSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        lvLista = (ListView) findViewById(R.id.lvLista);

        sentenciaSQL = new SentenciaSQL(VisualizarActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter =
                new NoticiaAdapter(VisualizarActivity.this, sentenciaSQL.obtenerNoticias());
        lvLista.setAdapter(adapter);
    }
}
