package com.jcodee.mod1class4.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jcodee.mod1class4.MainActivity;
import com.jcodee.mod1class4.fragmentos.FotoFragment;

/**
 * Created by johannfjs on 8/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class FotoAdapter extends FragmentPagerAdapter {

    public FotoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Creamos un fragmento
        FotoFragment fotoFragment = new FotoFragment();
        //Creamos la forma de pasar la información al fragmento
        Bundle bundle = new Bundle();
        //Añadimos los datos para pasar al fragmento
        bundle.putInt("position", position);
        //Cargamos los datos al fragmento
        fotoFragment.setArguments(bundle);
        //Retornamos el fragmento con los datos cargados
        return fotoFragment;
    }

    @Override
    public int getCount() {
        return MainActivity.lista.size();
    }
}
