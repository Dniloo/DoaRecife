package com.example.doarecife.doarecife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doarecife.doarecife.model.Item;
import com.unibratec.danilo.projetocds.model.Cd;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by misael-junior on 23/04/16.
 */
public class ItensAdapter extends ArrayAdapter<Item> {

    public ItensAdapter(Context context, List<Item> itens) {
        super(context, 0, itens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item itens = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(getContext()).load(itens.getFoto()).into(viewHolder.imageView);
        viewHolder.txtTitulo.setText(itens.getTipo());
        viewHolder.txtQuantidade.setText(itens.getQuantidade());

        return convertView;
    }

    static class ViewHolder{
        @Bind(R.id.image_tipo)
        ImageView imageView;
        @Bind(R.id.text_titulo)
        TextView txtTitulo;
        @Bind(R.id.text_quantidade)
        TextView txtQuantidade;

        public ViewHolder(View parent){
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }

}
