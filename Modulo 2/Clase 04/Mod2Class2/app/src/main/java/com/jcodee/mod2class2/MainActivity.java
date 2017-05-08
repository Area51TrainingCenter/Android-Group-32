package com.jcodee.mod2class2;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class2.adapter.TarjetaAdapter;
import com.jcodee.mod2class2.database.MetodosRealm;
import com.jcodee.mod2class2.entidades.TarjetaEntidad;
import com.jcodee.mod2class2.modelos.Tarjeta;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.etNumero)
    EditText etNumero;
    @BindView(R.id.tvCvv)
    EditText tvCvv;
    @BindView(R.id.tilNumero)
    TextInputLayout tilNumero;
    @BindView(R.id.etFecha)
    EditText etFecha;
    @BindView(R.id.tilFecha)
    TextInputLayout tilFecha;
    @BindView(R.id.tilCvv)
    TextInputLayout tilCvv;
    @BindView(R.id.rvDatos)
    RecyclerView rvDatos;
    private TarjetaAdapter adapter;
    private ArrayList<Tarjeta> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        etNumero.addTextChangedListener(new MyTextWatcher(etNumero));
        etFecha.addTextChangedListener(new MyTextWatcher(etFecha));
        tvCvv.addTextChangedListener(new MyTextWatcher(tvCvv));

        lista = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new TarjetaAdapter(MainActivity.this, lista);
        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvDatos.setAdapter(adapter);

        //Mostrar los registro previamente registrados
        lista.clear();
        RealmResults<TarjetaEntidad> listado = MetodosRealm.listarTodo();
        for (TarjetaEntidad item : listado) {
            Tarjeta tarjeta1 = new Tarjeta();
            tarjeta1.setId((int) item.getId());
            tarjeta1.setNumero(item.getNumero());
            tarjeta1.setFecha(item.getFecha());
            tarjeta1.setCvv(item.getCvv());
            lista.add(tarjeta1);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnRegistrar)
    public void onRegistrarClicked() {

        String numero = etNumero.getText().toString();
        String fecha = etFecha.getText().toString();
        String cvv = tvCvv.getText().toString();

        /*
        if (numero.isEmpty() || numero.trim().equals("")) {
            tilNumero.setError("El campo Número es requerido");
            return;
        } else {
            tilNumero.setError(null);
        }
        */

        if (fecha.isEmpty() || fecha.trim().equals("")) {
            tilFecha.setError("El campo Fecha es requerido");
            return;
        } else {
            tilFecha.setError(null);
        }
        if (cvv.isEmpty() || cvv.trim().equals("")) {
            tilCvv.setError("El campo CVV es requerido");
            return;
        } else {
            tilCvv.setError(null);
        }

        if (validarNumero()) {
            /*
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setId(lista.size() + 1);
            tarjeta.setNumero(numero);
            tarjeta.setFecha(fecha);
            tarjeta.setCvv(cvv);
            lista.add(tarjeta);
            */

            TarjetaEntidad tarjetaEntidad = new TarjetaEntidad();
            tarjetaEntidad.setId(MetodosRealm.nextId());
            tarjetaEntidad.setNumero(numero);
            tarjetaEntidad.setFecha(fecha);
            tarjetaEntidad.setCvv(cvv);
            MetodosRealm.registrarOActualizar(tarjetaEntidad);

            lista.clear();
            //UUID.randomUUID()
            RealmResults<TarjetaEntidad> listado = MetodosRealm.listarTodo();
            for (TarjetaEntidad item : listado) {
                Tarjeta tarjeta1 = new Tarjeta();
                tarjeta1.setId((int) item.getId());
                tarjeta1.setNumero(item.getNumero());
                tarjeta1.setFecha(item.getFecha());
                tarjeta1.setCvv(item.getCvv());
                lista.add(tarjeta1);
            }

            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Se registro.", Toast.LENGTH_SHORT).show();
        }

        /*
        InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
         */
    }

    @OnClick(R.id.ivCalendar)
    public void onCalendarClicked() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        etFecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            /*
            if (editText.getId() == R.id.etFecha) {
                String date = etFecha.getText().toString();
                if (date.length() == 2 || date.length() == 5) {
                    etFecha.setText(date + "/");
                }
            }
            */
        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (editText.getId()) {
                case R.id.etNumero:

                    /*
                    String numero = etNumero.getText().toString();
                    if (numero.isEmpty() || numero.trim().equals("")) {
                        tilNumero.setError("El campo Número es requerido");
                        return;
                    } else {
                        tilNumero.setError(null);
                    }
                    */
                    validarNumero();

                    break;
                case R.id.etFecha:

                    String fecha = etFecha.getText().toString();
                    if (fecha.isEmpty() || fecha.trim().equals("")) {
                        tilFecha.setError("El campo Fecha es requerido");
                        return;
                    } else {
                        tilFecha.setError(null);
                    }

                    break;
                case R.id.tvCvv:

                    String cvv = tvCvv.getText().toString();
                    if (cvv.isEmpty() || cvv.trim().equals("")) {
                        tilCvv.setError("El campo CVV es requerido");
                        return;
                    } else {
                        tilCvv.setError(null);
                    }

                    break;
            }
        }
    }

    private boolean validarNumero() {
        boolean estado = false;
        String numero = etNumero.getText().toString();
        if (numero.isEmpty() || numero.trim().equals("")) {
            tilNumero.setError("El campo Número es requerido");
        } else {
            tilNumero.setError(null);
            estado = true;
        }
        return estado;
    }

}
