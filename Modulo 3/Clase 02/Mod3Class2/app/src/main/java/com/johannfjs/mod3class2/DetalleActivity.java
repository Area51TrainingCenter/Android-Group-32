package com.johannfjs.mod3class2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.johannfjs.mod3class2.database.MetodosRealm;
import com.johannfjs.mod3class2.entidades.UbicacionEntidad;

import io.realm.RealmResults;

public class DetalleActivity extends AppCompatActivity {

    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                RealmResults<UbicacionEntidad> lista = MetodosRealm.obtenerPosiciones();

                for (UbicacionEntidad item : lista) {

                    //Agregamos las posiciones al mapa
                    Double latitud = item.getLatitud();
                    Double longitud = item.getLongitud();

                    googleMap.addMarker(
                            new MarkerOptions()
                                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_icono))
                                    .icon(BitmapDescriptorFactory.defaultMarker())
                                    .position(new LatLng(latitud, longitud))
                                    .title(item.getTitulo())
                                    .snippet(item.getDescripcion())
                    );

                    //Mover la camara a la ultima posicion
                    googleMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(new LatLng(latitud, longitud), 13));

                }

                googleMap.setMyLocationEnabled(true);


                //Tipos de Mapa
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


            }
        });
    }
}
