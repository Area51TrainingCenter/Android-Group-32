package com.jcodee.mod2class4;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class4.database.MetodosRealm;
import com.jcodee.mod2class4.entidad.UsuarioEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.tilNombre)
    TextInputLayout tilNombre;
    @BindView(R.id.etApellido)
    EditText etApellido;
    @BindView(R.id.tilApellido)
    TextInputLayout tilApellido;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.tilContrasena)
    TextInputLayout tilContrasena;
    @BindView(R.id.etRepContrasena)
    EditText etRepContrasena;
    @BindView(R.id.tilRepContrasena)
    TextInputLayout tilRepContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegistrar)
    public void onViewClicked() {
        UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
        usuarioEntidad.setId(MetodosRealm.getNextId());
        usuarioEntidad.setApellido(etApellido.getText().toString());
        usuarioEntidad.setNombre(etNombre.getText().toString());
        usuarioEntidad.setUsuario(etUsuario.getText().toString());
        usuarioEntidad.setContrasenia(etContrasena.getText().toString());

        MetodosRealm.insertarUsuario(usuarioEntidad);
        Toast.makeText(this, "Se registro el usuario", Toast.LENGTH_SHORT).show();

        finish();
    }
}
//Volley - OkHttp - Retrofit
