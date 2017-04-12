package com.jcodee.mod1class4;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcodee.mod1class4.adapters.FotoAdapter;

public class DetalleGaleriaActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_galeria);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new FotoAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //Darle animación
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        if (getIntent() != null) {
            if (getIntent().hasExtra("position")) {
                int position = getIntent().getIntExtra("position", -1);

                viewPager.setCurrentItem(position);
            }
        }
    }
}
