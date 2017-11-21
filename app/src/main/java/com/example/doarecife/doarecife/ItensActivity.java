package com.example.doarecife.doarecife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.example.doarecife.doarecife.model.Item;
import com.unibratec.danilo.projetocds.model.Cd;

import org.parceler.Parcels;

public class ItensActivity extends AppCompatActivity
    implements ListaItensFragment.CliqueNoItemListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }

    @Override
    public void itemFoiClicado(Item item) {
        if (getResources().getBoolean(R.bool.tablet)) {
            DetalheItemFragment dif = DetalheItemFragment.newInstance(item);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detalhe, dif, "detalhe")
                    .commit();
        } else {
            Intent it = new Intent(this, DetalheItemActivity.class);
            Parcelable p = Parcels.wrap(item);
            it.putExtra(DetalheItemActivity.EXTRA_ITEM, p);
            startActivity(it);
        }
    }
}
