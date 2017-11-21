package com.example.doarecife.doarecife;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doarecife.doarecife.model.Item;
import com.unibratec.danilo.projetocds.model.Cd;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetalheItemFragment extends Fragment {
    private static final String EXTRA_ITEM = "param1";

    @Bind(R.id.text_titulo)
    TextView mTextTitulo;
    @Bind(R.id.text_estado)
    TextView mTextEstado;
    @Bind(R.id.text_quantidade)
    TextView mTextQuantidade;
    

    private Item mItem;

  public static DetalheItemFragment newInstance(Item item) {
      DetalheItemFragment fragment = new DetalheItemFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(item);
        args.putParcelable(EXTRA_ITEM, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_ITEM);
            mItem =  Parcels.unwrap(p);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detalhe, container, false);
        ButterKnife.bind(this, view);

        mTextTitulo.setText(mItem.getTipo());
        mTextEstado.setText(mItem.getFoto());
        mTextQuantidade.setText(String.valueOf(mItem.getQuantidade()));
        return view;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.meuBotao)
    public void meubotao(){
        Toast.makeText(getContext(), "Buscando locais", Toast.LENGTH_SHORT).show();;
    }
}
