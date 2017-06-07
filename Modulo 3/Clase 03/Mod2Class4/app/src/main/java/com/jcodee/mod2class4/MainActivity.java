package com.jcodee.mod2class4;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.tilContrasena)
    TextInputLayout tilContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnIngresar)
    public void onBtnIngresarClicked() {

        String usuario = etUsuario.getText().toString();
        String contrasenia = etContrasena.getText().toString();

        UsuarioEntidad usuarioEntidad = MetodosRealm.validarUsuario(usuario, contrasenia);
        if (usuarioEntidad != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btnCrearCuenta)
    public void onBtnCrearCuentaClicked() {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }
}
