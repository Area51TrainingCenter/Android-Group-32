package com.jcodee.mod2class4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jcodee.mod2class4.fragmentos.DatosFragment;
import com.jcodee.mod2class4.fragmentos.EmpresaFragment;
import com.jcodee.mod2class4.fragmentos.FormularioFragment;
import com.jcodee.mod2class4.fragmentos.ListadoFragment;
import com.jcodee.mod2class4.fragmentos.RegistrarFragment;
import com.jcodee.mod2class4.fragmentos.RegistroPostFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //es para mostrar el contenido de la pantalla que decimos
        navigationView.getMenu().performIdentifierAction(R.id.nav_registrar, 0);
        //Es para pintar o seleccionar la opción en la cual te encuentras
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pasarDatos(String razonSocial, String ruc) {
        //Creamos el fragmento
        Fragment fragment = new DatosFragment();
        //Creamos el contenedor para pasar la información
        Bundle bundle = new Bundle();
        //Agregamos los parametros a enviar
        bundle.putString("razon_social", razonSocial);
        bundle.putString("ruc", ruc);
        //Cargamos los parametros al fragmento
        fragment.setArguments(bundle);

        //Reemplazamos el fragmento por el nuevo
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenido, fragment)
                //.addToBackStack(null)
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_registrar) {
            fragment = new RegistrarFragment();
        } else if (id == R.id.nav_listar) {
            fragment = new ListadoFragment();
        } else if (id == R.id.nav_registro_post) {
            fragment = new RegistroPostFragment();
        } else if (id == R.id.nav_formulario) {
            fragment = new FormularioFragment();
        } else if (id == R.id.nav_empresa) {
            fragment = new EmpresaFragment();
        } else if (id == R.id.nav_datos) {
            fragment = new DatosFragment();
        } else if (id == R.id.nav_cerrar_sesion) {
            //Limpiamos todas las pantallas que se mantienen en el historico
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
