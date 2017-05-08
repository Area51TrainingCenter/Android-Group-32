package com.jcodee.mod2class2;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class2.database.MetodosRealm;
import com.jcodee.mod2class2.entidades.TarjetaEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditarActivity extends AppCompatActivity {

    @BindView(R.id.etNumero)
    EditText etNumero;
    @BindView(R.id.tilNumero)
    TextInputLayout tilNumero;
    @BindView(R.id.etFecha)
    EditText etFecha;
    @BindView(R.id.tilFecha)
    TextInputLayout tilFecha;
    @BindView(R.id.tvCvv)
    EditText tvCvv;
    @BindView(R.id.tilCvv)
    TextInputLayout tilCvv;

    private long ID_TARJETA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivCalendar)
    public void onIvCalendarClicked() {
    }

    @OnClick(R.id.btnEditar)
    public void onBtnEditarClicked() {

        String numero = etNumero.getText().toString();
        String fecha = etFecha.getText().toString();
        String cvv = tvCvv.getText().toString();

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

        TarjetaEntidad tarjetaEntidad = new TarjetaEntidad();
        tarjetaEntidad.setId(ID_TARJETA);
        tarjetaEntidad.setCvv(cvv);
        tarjetaEntidad.setFecha(fecha);
        tarjetaEntidad.setNumero(numero);
        MetodosRealm.registrarOActualizar(tarjetaEntidad);
        Toast.makeText(this, "Se Actualizo", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null) {
            if (getIntent().hasExtra("idTarjeta")) {
                int idTarjeta = getIntent().getIntExtra("idTarjeta", -1);
                TarjetaEntidad tarjetaEntidad = MetodosRealm.obtenerTarjetaPorId(idTarjeta);
                etFecha.setText(tarjetaEntidad.getFecha());
                etNumero.setText(tarjetaEntidad.getNumero());
                tvCvv.setText(tarjetaEntidad.getCvv());
                ID_TARJETA = tarjetaEntidad.getId();
            }
        }
    }
}
