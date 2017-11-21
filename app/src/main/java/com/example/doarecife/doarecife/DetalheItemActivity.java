package com.example.doarecife.doarecife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.doarecife.doarecife.model.Item;
import com.unibratec.danilo.projetocds.model.Cd;

import org.parceler.Parcels;

public class DetalheItemActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Item item = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ITEM));

        DetalheItemFragment dif = DetalheItemFragment.newInstance(item);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, dif, "detalhe")
                .commit();
    }
}
